package com.zhizu.common.cat.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.AbstractMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.rpc.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/6
 **/
@Activate(group = {"provider", "consumer"}, order = -9000)
public class DubboCatFilter implements Filter {

    private static final ThreadLocal<Cat.Context> CAT_CONTEXT = new ThreadLocal<Cat.Context>();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        String sideKey = url.getParameter(Constants.SIDE_KEY);
        String loggerName = invoker.getInterface().getSimpleName() + "." + invocation.getMethodName();
        String type = "PigeonCall";
        if (Constants.PROVIDER_SIDE.equals(sideKey)) {
            type = "PigeonService";
        }
        Transaction t = Cat.newTransaction(type, loggerName);
        Result result = null;
        try {
            Cat.Context context = getContext();
            if (Constants.CONSUMER_SIDE.equals(sideKey)) {
                createConsumerCross(url, t);
                Cat.logRemoteCallClient(context);
            } else {
                createProviderCross(url, t);
                Cat.logRemoteCallServer(context);
            }
            setAttachment(context);
            result = invoker.invoke(invocation);
 
            if (result.hasException()) {
                //给调用接口出现异常进行打点
                Throwable throwable = result.getException();
                Event event = null;
                if (RpcException.class == throwable.getClass()) {
                    Throwable caseBy = throwable.getCause();
                    if (caseBy != null && caseBy.getClass() == org.apache.dubbo.remoting.TimeoutException.class) {
                        event = Cat.newEvent("DUBBO_TIMEOUT_ERROR", loggerName);
                    } else {
                        event = Cat.newEvent("DUBBO_REMOTING_ERROR", loggerName);
                    }
                } else if (RemotingException.class.isAssignableFrom(throwable.getClass())) {
                    event = Cat.newEvent("DUBBO_REMOTING_ERROR", loggerName);
                }else{
                    event = Cat.newEvent("DUBBO_BIZ_ERROR", loggerName);
                }
                event.setStatus(result.getException());
                completeEvent(event);
                t.addChild(event);
                t.setStatus(result.getException().getClass().getSimpleName());
            } else {
                t.setStatus(Message.SUCCESS);
            }
            return result;
        } catch (RuntimeException e) {
            Event event = null;
            if (RpcException.class == e.getClass()) {
                Throwable caseBy = e.getCause();
                if (caseBy !=null && caseBy.getClass() == org.apache.dubbo.remoting.TimeoutException.class) {
                    event = Cat.newEvent("DUBBO_TIMEOUT_ERROR", loggerName);
                } else {
                    event = Cat.newEvent("DUBBO_REMOTING_ERROR", loggerName);
                }
            } else {
                event = Cat.newEvent("DUBBO_BIZ_ERROR", loggerName);
            }
            event.setStatus(e);
            completeEvent(event);
            t.addChild(event);
            t.setStatus(e.getClass().getSimpleName());
            if (result == null) {
                throw e;
            } else {
                return result;
            }
        } finally {
            t.complete();
            CAT_CONTEXT.remove();
        }
    }

    static class DubboCatContext implements Cat.Context {
        private Map<String,String> properties = new HashMap<String, String>();

        @Override
        public void addProperty(String key, String value) {
            properties.put(key,value);
        }

        @Override
        public String getProperty(String key) {
            return properties.get(key);
        }
    }

    private void setAttachment(Cat.Context context) {
        RpcContext.getContext().setAttachment(Cat.Context.ROOT,context.getProperty(Cat.Context.ROOT));
        RpcContext.getContext().setAttachment(Cat.Context.CHILD,context.getProperty(Cat.Context.CHILD));
        RpcContext.getContext().setAttachment(Cat.Context.PARENT,context.getProperty(Cat.Context.PARENT));
    }

    private Cat.Context getContext(){
        Cat.Context context = CAT_CONTEXT.get();
        if (context==null) {
            context = initContext();
            CAT_CONTEXT.set(context);
        }
        return context;
    }

    private Cat.Context initContext() {
        Cat.Context context = new DubboCatContext();
        Map<String,String> attachments = RpcContext.getContext().getAttachments();
        if (attachments!=null&&attachments.size()>0) {
            for (Map.Entry<String,String> entry:attachments.entrySet()) {
                if (Cat.Context.CHILD.equals(entry.getKey()) || Cat.Context.ROOT.equals(entry.getKey()) || Cat.Context.PARENT.equals(entry.getKey())) {
                    context.addProperty(entry.getKey(),entry.getValue());
                }
            }
        }
        return context;
    }

    private void createConsumerCross(URL url, Transaction t) {
        Event crossAppEvent = Cat.newEvent("PigeonCall.app", getProviderAppName(url));
        Event crossServerEvent = Cat.newEvent("PigeonCall.server", url.getHost());
        Event crossPortEvent = Cat.newEvent("PigeonCall.port", url.getPort() + "");
        crossAppEvent.setStatus(Event.SUCCESS);
        crossServerEvent.setStatus(Event.SUCCESS);
        crossPortEvent.setStatus(Event.SUCCESS);
        completeEvent(crossAppEvent);
        completeEvent(crossPortEvent);
        completeEvent(crossServerEvent);
        t.addChild(crossAppEvent);
        t.addChild(crossPortEvent);
        t.addChild(crossServerEvent);
    }

    private void createProviderCross(URL url, Transaction t) {
        String consumerAppName = RpcContext.getContext().getAttachment(Constants.APPLICATION_KEY);
        if (StringUtils.isEmpty(consumerAppName)) {
            consumerAppName = RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort();
        }
        Event crossAppEvent = Cat.newEvent("PigeonService.app", consumerAppName);
        Event crossServerEvent = Cat.newEvent("PigeonService.client", url.getHost());
        crossAppEvent.setStatus(Event.SUCCESS);
        crossServerEvent.setStatus(Event.SUCCESS);
        completeEvent(crossAppEvent);
        completeEvent(crossServerEvent);
        t.addChild(crossAppEvent);
        t.addChild(crossServerEvent);
    }

    private void completeEvent(Event event) {
        AbstractMessage message = (AbstractMessage) event;
        message.setCompleted(true);
    }

    private String getProviderAppName(URL url){
        String appName = url.getParameter("serverApplicationName");
        if(StringUtils.isEmpty(appName)){
            String interfaceName  = url.getParameter(Constants.INTERFACE_KEY);
            appName = interfaceName.substring(0,interfaceName.lastIndexOf('.'));
        }
        return appName;
    }

}
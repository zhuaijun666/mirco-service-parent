import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import javassist.expr.NewArray;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import tk.mybatis.mapper.genid.GenId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RunWith(JUnit4.class)
public class Appsimulator {
    public Map<String, String> maps = new HashMap<String, String>();

    public Cat.Context context;

    @Test
    public void simulateHierarchyTransaction() throws Exception {
//        context = new Cat.Context() {
//            @Override
//            public void addProperty(String key, String value) { maps.put(key, value); }
//
//            @Override
//            public String getProperty(String key) { return maps.get(key); }
//        };
//        //RPC调用开始
//        rpcClient();

        while(true) {
            Cat.logMetricForCount("metric.key");
            Thread.sleep(1000);
        }


    }

    protected void rpcClient() {
        //客户端埋点，Domain为RpcClient，调用服务端提供的Echo服务
        Transaction parent = Cat.newTransaction("Call", "CallServiceEcho");
        Cat.getManager().getThreadLocalMessageTree().setDomain("RpcClient");

        Cat.logEvent("Call.server","localhost");
        Cat.logEvent("Call.app","RpcService");
        Cat.logEvent("Call.port","8888");
        Cat.logRemoteCallClient(context, "RpcClient");

        //开启新线程模拟远程RPC服务，将context上传到 RPC 服务器
        rpcService(context, "server");

        parent.complete();
    }

    protected void rpcService(final Cat.Context context, final String clientDomain) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //服务器埋点，Domain为 RpcService 提供Echo服务
                Transaction child = Cat.newTransaction("Service", "Echo");
                Cat.getManager().getThreadLocalMessageTree().setDomain("RpcService");

                Cat.logEvent("Service.client", "127.0.0.1"); //填客户端地址
                Cat.logEvent("Service.app", clientDomain);
                Cat.logRemoteCallServer(context);

                //to do your business

                child.complete();
            }
        };

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
        }
    }

    private  final static BlockingQueue queue = new ArrayBlockingQueue(6*1024);

    @Test
    public void batchProcess() throws Exception {

        Executor executor = Executors.newFixedThreadPool(1);


        executor.execute(new consumer());

        while (true) {
            queue.offer(111);


        }







    }

    public static class consumer implements Runnable{

        @Override
        public void run() {

            List list = new ArrayList();
            long lastTime = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - lastTime > 3000l){
                    // process
                    if (!list.isEmpty()) {

                    }

                    System.out.println("tinme");
                    list =  new ArrayList();
                    lastTime = System.currentTimeMillis();
                }

                if (list.size() > 200) {
                    // process

                    System.out.println("size");
                    list =  new ArrayList();
                }

                try {
                    Object object = queue.poll(1l, TimeUnit.SECONDS);

                    if (object != null) {
                        list.add(object);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void produceEle(Object obj) {
        if (!this.queue.offer(obj)) {
            System.out.println("error ");
        }
    }
}
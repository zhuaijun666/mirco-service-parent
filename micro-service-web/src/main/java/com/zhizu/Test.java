//package com.zhizu;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import com.dianping.cat.Cat;
//import com.google.gson.Gson;
//import com.sun.org.apache.bcel.internal.generic.NEW;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import com.xxl.job.core.log.XxlJobLogger;
//import lombok.extern.slf4j.Slf4j;
//import sun.misc.BASE64Encoder;
//import sun.util.resources.cldr.ta.CalendarData_ta_IN;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author zhuaijun
// * @Description //TODO
// * @Date 2020/7/19
// **/
//@Slf4j
//public class Test {
//
////    @XxlJob("getMqReadyJobHandler")
//    public static ReturnT<String> demoJobHandler(String param) throws Exception {
//        XxlJobLogger.log("XXL-JOB, getMqReadyJobHandler.");
//
//        String rabbitmqHost = "117.78.44.119";
//        String rabbitmqPort = "15672";
//        String userName = "zhizumq";
//        String password = "zhizumqQ3";
//
//        String url = String.format("http://%s:%s/api/vhosts",rabbitmqHost,rabbitmqPort);
//
//
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        String base64Str = String.format("%s:%s",userName,password);
//        String base64Val = base64Encoder.encode(base64Str.getBytes());
//
//        Map<String,String> headerMap = new HashMap<>();
//        String authHash = String.format("Basic %s",base64Val );
//        headerMap.put("authorization",authHash);
//
//        HttpResponse httpResponse = HttpRequest.get(url).addHeaders(headerMap).timeout(3 * 1000).execute();
//        int status = httpResponse.getStatus();
//
//        System.out.println(httpResponse.body());
//
//
//        if (status == 200) {
//            RabbitMqQueueInfoByVhosts[] rabbitMqQueueInfoByVhostsArray = new Gson().fromJson(httpResponse.body(), RabbitMqQueueInfoByVhosts[].class);
//
//            if (rabbitMqQueueInfoByVhostsArray != null && rabbitMqQueueInfoByVhostsArray.length > 0) {
//                for (RabbitMqQueueInfoByVhosts rabbitMqQueueInfoByVhosts : rabbitMqQueueInfoByVhostsArray) {
//                    if (rabbitMqQueueInfoByVhosts.getMessages_ready() != null) {
//                       Cat.getProducer().logEvent();
//                        Cat.logMetricForCount("service.mq.ready", rabbitMqQueueInfoByVhosts.getMessages_ready());
//                    }
//                }
//
//            }
//        } else {
//            return ReturnT.FAIL;
//        }
//
//
//        return ReturnT.SUCCESS;
//    }
//
//    public static void main(String[] args) throws Exception {
//        demoJobHandler(null);
//    }
//}

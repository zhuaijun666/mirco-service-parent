import cn.hutool.core.date.DateUtil;
import com.dianping.cat.Cat;
import com.github.pagehelper.PageHelper;
import com.zhizu.dao.model.AppAuth;
import com.zhizu.service.impl.AppAuthServiceImpl;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/28
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(JUnit4.class)
public class Test {
    @Autowired
    private AppAuthServiceImpl deviceServiceImpl;

    @org.junit.Test
    public void Test() {
        AppAuth appAuth = new AppAuth();
        appAuth.setAppKey("test");
        appAuth.setAppSecret("test");
        appAuth.setUserId("test");
        appAuth.setEnabled(new Byte("1"));
        appAuth.setDateCreated(DateUtil.date());
        appAuth.setDateCreated(DateUtil.date());

        deviceServiceImpl.insert(appAuth);
        appAuth.setId(null);
        deviceServiceImpl.insert(appAuth);
        appAuth.setId(null);
        deviceServiceImpl.insert(appAuth);
    }

    @org.junit.Test
    public void  searchTest() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new consumer1());
        executor.execute(new consumer2());

        Thread.sleep(300000L);

    }

    public static class consumer1 implements Runnable{

        @Override
        public void run() {
            while (true) {
                System.out.println("consumer1");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class consumer2 implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("consumer2");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }



}

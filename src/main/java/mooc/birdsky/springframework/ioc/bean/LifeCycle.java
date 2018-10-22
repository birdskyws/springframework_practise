package mooc.birdsky.springframework.ioc.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class LifeCycle implements InitializingBean,DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("类被回收，调用destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("类被初始化，调用afterPropertiesSet");
    }
//    public void start(){
//        System.out.println("类被初始化");
//    }
//    public void stop()
//    {
//        System.out.println("类被回收");
//    }
}

package mooc.birdsky.springframework.aop.aspectj.biz;

import mooc.birdsky.springframework.aop.aspectj.MoocMethod;
import org.springframework.stereotype.Component;

@Component
public class AspectBiz {

    @MoocMethod("MoocMethod with annotation")
    public String say(String arg)
    {
        String ret = "说一些话："+arg;
        System.out.println(ret);
        //throw new RuntimeException("say failed");
        return ret;
    }


}

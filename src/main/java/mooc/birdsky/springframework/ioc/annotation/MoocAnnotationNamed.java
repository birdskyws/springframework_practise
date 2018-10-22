package mooc.birdsky.springframework.ioc.annotation;

import org.springframework.stereotype.Component;

@Component("bean")
public class MoocAnnotationNamed {
    public void say(String arg)
    {
        System.out.println("Annotation:"+arg);
    }
}

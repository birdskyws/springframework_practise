package mooc.birdsky.springframework.ioc.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class MoocAnnotation {
    public void say(String arg)
    {
        System.out.println("Annotation:"+arg);
    }
}

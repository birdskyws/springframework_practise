package mooc.birdsky.springframework.ioc.schema.componentscan;

import org.springframework.stereotype.Component;

@Component
public class ComponentScan2 {
    public void say(String arg)
    {
        System.out.println("hello2:"+arg);
    }
}

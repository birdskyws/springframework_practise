package mooc.birdsky.springframework.ioc.annotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class BeanInterfaceOne implements BeanInterface {
    @Override
    public void say(String arg){
        System.out.println("bean one say"+arg);
    }
}

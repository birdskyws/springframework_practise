package mooc.birdsky.springframework.ioc.annotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(2)
@Component
public class BeanInterfaceTwo implements BeanInterface {
    @Override
    public void say(String arg){
        System.out.println("bean one say"+arg);
    }
}

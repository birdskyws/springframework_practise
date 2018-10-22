package mooc.birdsky.springframework.ioc.autowire;

public class AutowireServiceImpl implements AutowireService {

    private AutowireDAO autowireDAO2;


    public void setAutowireDAO2(AutowireDAO autowireDAO2) {
        this.autowireDAO2 = autowireDAO2;
    }

    public void say(String arg)
    {
        System.out.println("service2 :"+arg);
        autowireDAO2.say(arg);
    }
}

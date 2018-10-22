package mooc.birdsky.springframework.ioc.autowire;

public class AutowireDAOImpl implements AutowireDAO {
    @Override
    public void say(String arg){
        System.out.println("output："+arg);
    }
}

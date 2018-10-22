package mooc.birdsky.springframework.ioc.annotation.Configuration;

public class StringStore implements Store {
    @Override
    public void say(String arg) {
        System.out.println("String store say"+arg);
    }
}

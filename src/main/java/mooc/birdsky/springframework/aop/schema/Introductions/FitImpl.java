package mooc.birdsky.springframework.aop.schema.Introductions;

public class FitImpl implements Fit {

    @Override
    public void filter(){
        System.out.println("Fit filter");
    }
}

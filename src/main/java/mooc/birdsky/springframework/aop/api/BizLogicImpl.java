package mooc.birdsky.springframework.aop.api;

public class BizLogicImpl implements BizLogic {

    @Override
    public String save(String arg)
    {
        System.out.println("business input arg");
        return arg+" implements ";
    }
}

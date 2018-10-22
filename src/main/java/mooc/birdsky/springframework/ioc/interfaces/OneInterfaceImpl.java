package mooc.birdsky.springframework.ioc.interfaces;

/**
 * 时间：2018.10.17
 * 功能：实现接口测试
 */
public class OneInterfaceImpl implements OneInterface {
    @Override
    public String hello(String word) {
        return "word form interface \"word\":"+word;
    }
}

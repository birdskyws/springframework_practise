package mooc.birdsky.springframework.ioc.interfaces;

import mooc.birdsky.springframework.ioc.injection.service.InjectionService;
import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/***
 * 测试 设置/构造注入
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {
    public TestInjection(){
        //super("classpath*:spring-injection.xml");
        super("classpath*:/ioc/spring-injection-constructor.xml");
    }

    @Test
    public void TestInjectionWithSetter()
    {
        InjectionService service = super.getBean("injectionService");
        service.save("你好");
        System.out.println(service.getClass().getName());
    }
    @Test
    public void TestInjectionWithConstructor()
    {
        InjectionService service = super.getBean("injectionService");
        service.save("你好");
    }
}

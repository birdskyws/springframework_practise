package mooc.birdsky.springframework.ioc.autowire;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class AutowireTest extends UnitTestBase {
    public AutowireTest()
    {
        super("classpath*:/ioc/spring-autowire.xml");
    }
    @Test
    public void TestAutowirebyName(){
        AutowireService service = super.getBean("autowireService");
        service.say("测试service");
    }
}

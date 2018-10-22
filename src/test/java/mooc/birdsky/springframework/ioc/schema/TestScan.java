package mooc.birdsky.springframework.ioc.schema;

import mooc.birdsky.springframework.ioc.schema.componentscan.ComponentScan1;
import mooc.birdsky.springframework.ioc.schema.componentscan.ComponentScan2;
import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.awt.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestScan extends UnitTestBase {

    public TestScan()
    {
        super("classpath*:/ioc/spring-ioc-componentscan.xml");
    }

    @Test
    public void TestComponentScan()
    {
        ComponentScan2 bean = super.getBean("componentScan2");
        bean.say("123");
    }
}

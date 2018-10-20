package org.birdsky.autowire;

import org.birdsky.base.UnitTestBase;
import org.birdsky.ioc.autowire.AutowireService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowire;

@RunWith(BlockJUnit4ClassRunner.class)
public class AutowireTest extends UnitTestBase {
    public AutowireTest()
    {
        super("classpath*:spring-autowire.xml");
    }
    @Test
    public void TestAutowirebyName(){
        AutowireService service = super.getBean("autowireService");
        service.say("测试service");
    }
}

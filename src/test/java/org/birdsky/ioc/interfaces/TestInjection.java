package org.birdsky.ioc.interfaces;

import org.birdsky.base.UnitTestBase;
import org.birdsky.ioc.injection.service.InjectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {
    public TestInjection(){
        //super("classpath*:spring-injection.xml");
        super("classpath*:spring-injection-constructor.xml");
    }

    @Test
    public void TestInjectionWithSetter()
    {
        InjectionService service = super.getBean("injectionService");
        service.save("你好");
    }
    @Test
    public void TestInjectionWithConstructor()
    {
        InjectionService service = super.getBean("injectionService");
        service.save("你好");
    }
}

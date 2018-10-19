package org.birdsky.ioc.interfaces;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestOneInterface extends UnitTestBase {
    public TestOneInterface()
    {
        super("classpath*:spring-ioc.xml");
    }
    @Test
    public void TestHello()
    {
        OneInterface one = new OneInterfaceImpl();
        System.out.println(one.hello("这是一个测试类"));
    }
    @Test
    public void TestBeanHello()
    {
        OneInterface oneInterface = super.getBean("oneInterface");

        System.out.println(oneInterface.hello("This is a test."));
    }

}

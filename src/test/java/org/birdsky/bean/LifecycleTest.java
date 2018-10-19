package org.birdsky.bean;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class LifecycleTest extends UnitTestBase {
    public LifecycleTest()
    {
        super("classpath*:spring-lifecycle.xml");
    }

    @Test
    public void TestLifecycle()
    {
        super.getBean("lifeCycle");
    }
}

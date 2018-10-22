package mooc.birdsky.springframework.ioc.bean;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * 测试生命周期，定义初始化函数和回收函数
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class LifecycleTest extends UnitTestBase {
    public LifecycleTest()
    {
        super("classpath*:/ioc/spring-lifecycle.xml");
    }

    @Test
    public void TestLifecycle()
    {
        super.getBean("lifeCycle");
    }
}

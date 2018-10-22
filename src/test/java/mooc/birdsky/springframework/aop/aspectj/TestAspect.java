package mooc.birdsky.springframework.aop.aspectj;

import mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz;
import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAspect extends UnitTestBase {
    public TestAspect(){
        super("classpath*:/aop/spring-aop-aspectj.xml");
    }

    @Test
    public void TestAspectBefore()
    {
        AspectBiz biz = super.getBean("aspectBiz");
        biz.say("开始做business 工作");
    }
}

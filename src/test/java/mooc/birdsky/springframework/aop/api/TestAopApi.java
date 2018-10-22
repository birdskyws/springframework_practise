package mooc.birdsky.springframework.aop.api;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAopApi extends UnitTestBase {
    public TestAopApi()
    {
        super("classpath*:/aop/spring-aop-api.xml");
        //Todo:classpath使用方法
    }

    @Test
    public void TestAopSave()
    {
        BizLogic biz = super.getBean("bizLogicImpl");
        biz.save("1234");
    }
}

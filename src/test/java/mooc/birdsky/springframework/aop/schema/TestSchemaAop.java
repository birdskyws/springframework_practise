package mooc.birdsky.springframework.aop.schema;

import mooc.birdsky.springframework.aop.schema.AspectBiz.AspectBiz;
import mooc.birdsky.springframework.aop.schema.Introductions.Fit;
import mooc.birdsky.springframework.aop.schema.tracking.UsageTrack;
import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestSchemaAop extends UnitTestBase {
    public TestSchemaAop()
    {

//        super("classpath*:/aop/spring-aop-schema-advice.xml");
        super("classpath*:/aop/spring-aop-schema-example.xml");
    }
    @Test
    public void TestSchema()
    {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        System.out.println("ioc "+aspectBiz.getClass().getName());
        aspectBiz.biz();
        /**
         * MoocAspect before
         * AspectBiz biz
         * MoocAspect after return
         * MoocAspect after final
         */
    }
    @Test
    public void TestSchemaThrow()
    {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        System.out.println("ioc "+aspectBiz.getClass().getName());
        aspectBiz.bizThrow();
        /**
         * MoocAspect before
         * round before process
         * AspectBiz bizThrow
         * round after process
         * MoocAspect after final
         * MoocAspect after return
         * java.lang.RuntimeException
         */
    }
    /** Todo:
     *  对抛出异常的处理，没有分析清楚！
     */

    @Test
    public void TestSchemaWithParams()
    {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        aspectBiz.init("hello world",3);
        /**
         * MoocAspect before
         * round before process
         * round before init process and bizName:hello world times:3
         * bizName:hello world try times:3
         * round after init process
         * round after process
         * MoocAspect after final
         * MoocAspect after return
         */
    }
    //Todo: execution 统配写法

    @Test
    public void TestSchemaParentsDeclare()
    {
        Fit fit = (Fit) super.getBean("aspectBiz");
        fit.filter();
    }

    //Todo: advise 多次尝试的例子

    @Test
    public void TestAopParentConstruct()
    {
        UsageTrack biz = (UsageTrack) super.getBean("aspectBiz");
        biz.printUsecount();
        System.out.println(biz.getClass().getName());
    }

    @Test
    public void TestConcurrentOperate()
    {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        aspectBiz.biz();
        aspectBiz.bizThrow();
        //Todo: 没有处理抛出的异常
        /**
         * Try times : 1
         *
         * 十月 22, 2018 5:53:48 下午 org.springframework.context.support.AbstractApplicationContext doClose
         * 信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@233c0b17: startup date [Mon Oct 22 17:53:47 CST 2018]; root of context hierarchy
         * AspectBiz biz
         * Try times : 1
         * Try times : 2
         * Try times : 3
         * Try times : 4
         * Try error : 4
         *
         * java.lang.RuntimeException
         */
    }


}

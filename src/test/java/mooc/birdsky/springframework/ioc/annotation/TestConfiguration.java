package mooc.birdsky.springframework.ioc.annotation;

import org.birdsky.base.UnitTestBase;
import mooc.birdsky.springframework.ioc.annotation.Configuration.Store;
import mooc.birdsky.springframework.ioc.annotation.Configuration.generic.GenericAutowireBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestConfiguration extends UnitTestBase {
    public TestConfiguration()
    {
        super("classpath*:/ioc/spring-beanannotation.xml");
    }

    @Test
    public void TestBean()
    {
        Store store = super.getBean("stringStore");
        System.out.println(store.getClass().getName());
    }

    @Test
    public void TestGeneric()
    {
        GenericAutowireBean bean = super.getBean("genericAutowireBean");
        bean.say();
    }
}

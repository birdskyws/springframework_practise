package mooc.birdsky.springframework.ioc.annotation;

import org.birdsky.base.UnitTestBase;
import mooc.birdsky.springframework.ioc.annotation.multibean.BeanList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAutoWiredList extends UnitTestBase {
    public TestAutoWiredList()
    {
        super("classpath*:/ioc/spring-beanannotation.xml");
    }

    @Test
    public void TestWiredList()
    {
        BeanList beanList = super.getBean("beanList");
        beanList.printList();
    }

    @Test
    public void TestWiredMap()
    {
        BeanList beanList = super.getBean("beanList");
        beanList.printMap();
        /**
         * Name:beanInterfaceOne
         * value type：mooc.birdsky.springframework.ioc.annotation.multibean.BeanInterfaceOne
         * Name:beanInterfaceTwo
         * value type：mooc.birdsky.springframework.ioc.annotation.multibean.BeanInterfaceTwo
         */
    }
}

package mooc.birdsky.springframework.ioc.aware;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
//import mooc.birdsky.springframework.ioc.aware.ApplicationContextAWARE;


@RunWith(BlockJUnit4ClassRunner.class)
public class AwareTest extends UnitTestBase {
    public AwareTest()
    {
        super("classpath*:/ioc/spring-aware.xml");
    }

    @Test
    public void printApplication()
    {
        ApplicationContextAWARE applicationContextAWARE = super.getBean("applcationAWARE");
        System.out.println(applicationContextAWARE.hashCode());

        applicationContextAWARE.printSelf();
    }
}

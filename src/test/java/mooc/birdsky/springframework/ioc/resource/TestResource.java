package mooc.birdsky.springframework.ioc.resource;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestResource extends UnitTestBase {
    public TestResource(){
        super("classpath*:/ioc/spring-resource.xml");
    }
    @Test
    public void TestResourceLoader()
    {
        MoocResource mooc = super.getBean("moocResource");
        try {
            mooc.readResource();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

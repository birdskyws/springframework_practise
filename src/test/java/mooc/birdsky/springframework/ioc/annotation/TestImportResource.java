package mooc.birdsky.springframework.ioc.annotation;

import org.birdsky.base.UnitTestBase;
import mooc.birdsky.springframework.ioc.annotation.Configuration.importresource.MyDriveManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestImportResource extends UnitTestBase {
    public TestImportResource(){
        super("classpath*:/ioc/spring-beanannotation.xml");
    }
    @Test
    public void TestResource(){
        MyDriveManager driveManager = getBean("driveManager");
        System.out.println(driveManager.toString());
    }

}

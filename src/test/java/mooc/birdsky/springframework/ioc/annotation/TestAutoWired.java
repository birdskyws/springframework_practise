package mooc.birdsky.springframework.ioc.annotation;

import org.birdsky.base.UnitTestBase;
import mooc.birdsky.springframework.ioc.annotation.injection.service.InjectionService;
import mooc.birdsky.springframework.ioc.annotation.injection.service.InjectionServiceImpl;
import org.junit.Test;

public class TestAutoWired extends UnitTestBase {
    public TestAutoWired()
    {
        super("classpath*:/ioc/spring-beanannotation.xml");
    }
    @Test
    public void TestDAOAutoWired()
    {
        InjectionService service = super.getBean("injectionServiceImpl");
        service.save("some data");
        System.out.println(service.getClass().getName());
        InjectionServiceImpl impl = (InjectionServiceImpl)service;
        System.out.println(impl.getInjectionDAO().getClass().getName());
    }
}

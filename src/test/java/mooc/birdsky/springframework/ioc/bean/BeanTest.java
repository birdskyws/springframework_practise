package mooc.birdsky.springframework.ioc.bean;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;

/**
 * 测试scope ，创建单例和多例。
 */
public class BeanTest extends UnitTestBase {
    public BeanTest(){
        super("classpath*:/ioc/spring-scope.xml");
    }

    @Test
    public void TestScope()
    {
        Scope scope = super.getBean("scope");
        scope.say();

        Scope scope1 = super.getBean("scope");
        scope1.say();
    }
}

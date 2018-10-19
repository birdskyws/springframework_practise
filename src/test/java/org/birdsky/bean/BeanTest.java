package org.birdsky.bean;

import org.birdsky.base.UnitTestBase;
import org.birdsky.ioc.bean.Scope;
import org.junit.Test;

public class BeanTest extends UnitTestBase {
    public BeanTest(){
        super("classpath*:spring-scope.xml");
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

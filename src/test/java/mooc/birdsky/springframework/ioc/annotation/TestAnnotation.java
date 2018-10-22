package mooc.birdsky.springframework.ioc.annotation;

import org.birdsky.base.UnitTestBase;
import org.junit.Test;

public class TestAnnotation extends UnitTestBase {
    public TestAnnotation()
    {
        super("classpath*:/ioc/spring-beanannotation.xml");
    }
    @Test
    public void TestAnnotation()
    {
        MoocAnnotation annotation = super.getBean("moocAnnotation");
        annotation.say("auto bean load");
    }
    @Test
    public void TestNamedAnnotation()
    {
        /**
         * 在类声明时，采用@Component("bean")方式注解
         * getBean传入bean
         */
        MoocAnnotationNamed annotation = super.getBean("bean");
        annotation.say("named bean load");
    }
    @Test
    public void TestAnnotationScope()
    {
        /**
         * @Scope 注解 默认 单例
         */
        MoocAnnotation annotation = super.getBean("moocAnnotation");
        System.out.println(annotation.hashCode());
        MoocAnnotation annotation2 = super.getBean("moocAnnotation");
        System.out.println(annotation2.hashCode());
    }

    @Test
    public void TestWithoutAnnotation()
    {
        /**
         * 在配置文件中指定了包名，还是要在类上进行注解声明
         * <context:component-scan base-package="mooc.birdsky.springframework.ioc.annotation">
         * 在 MoocWithoutAnnotation 上没有进行注解声明，所以下面的代码出错。
         */
        MoocWithoutAnnotation annotation = super.getBean("moocWithoutAnnotation");
        System.out.println(annotation.getClass().getName());
    }
}

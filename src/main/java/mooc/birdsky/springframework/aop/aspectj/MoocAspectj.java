package mooc.birdsky.springframework.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MoocAspectj {


    @Pointcut("execution(* mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz.*(..))")
    public void pointcut(){ }

    @Pointcut("within(mooc.birdsky.springframework.aop.aspectj.biz.*)")
    public void bizPointcut(){}

    /**
     * 注入点定义：
     * 1.execution描述
     * 2.pointcut描述
     * 3.pointcut + 参数
     * 4.pointcut + 注解 //Todo: 注解使用
     */
    @Before("execution(* mooc.birdsky.springframework.aop.aspectj.biz.AspectBiz.*(..))")
    public void before(){
        System.out.println("Aspectj exec before1");
    }

    @Before("pointcut()")
    public void before2()
    {
        System.out.println("Aspectj exec before2 ");
    }

    @Before("pointcut() && args(arg)")
    public void before3(String arg)
    {
        System.out.println("Aspectj exec before3 withParam:"+arg);
    }

    @Before("pointcut() && @annotation(moocMethod)")
    public void before4(MoocMethod moocMethod)
    {
        System.out.println("Aspectj exec before4 withAnotation and param:"+moocMethod.value());
    }


    @AfterReturning(pointcut="pointcut()",returning="retval")
    public void doAfterReturning(Object retval)
    {
        System.out.println("doAfterReturning do "+retval);
    }

    @AfterThrowing(pointcut = "pointcut()",throwing = "e")
    public void doAfterThrowing(RuntimeException e)
    {
        System.out.println("doAfterThrowing "+e.getMessage());
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp ) throws Throwable{
        System.out.println("Around1");
        Object obj = pjp.proceed();
        System.out.println("Around2:"+obj);
        return obj;
    }
}

package mooc.birdsky.springframework.aop.api;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class BirdskyBeforeAdvice implements MethodBeforeAdvice,ThrowsAdvice,AfterReturningAdvice , MethodInterceptor {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("BirdskyBeforeAdvice:"+method.getName());
    }
    //Todo Throwable

    /**
     * throws-advice 如果抛出异常，那么它将覆盖原有异常
     */
    public void afterThrowing(Exception e) throws Throwable{
        System.out.println("Birdsky afterThrow1 ");
    }

    public void afterThrowing(Method method,Exception e) throws Throwable{
        System.out.println("Birdsky afterThrow2 method:" + method.getName());
    }


    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("Birdsky afterReturning method"+method.getName()+"targer :"+target.getClass().getName());
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("method:"+invocation.getMethod().getName());
        System.out.println("target:"+invocation.getStaticPart().getClass().getName());
        Object obj = invocation.proceed();
        return obj;
    }
}

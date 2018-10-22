package mooc.birdsky.springframework.aop.api;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class BirdskyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method :"+target.getClass().getName());
        System.out.println("returnValue :"+returnValue);
    }
}

package mooc.birdsky.springframework.aop.schema.concurrent;

import org.aspectj.lang.ProceedingJoinPoint;



public class ConcurrentOperationExecutor {
    private int maxRetries ;
    private int order;

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable
    {
        int numAttempts = 0;
        RuntimeException exception;
        do {
            numAttempts++;
            System.out.println("Try times : " + numAttempts);
            try {
                return pjp.proceed();
            } catch (RuntimeException ex) {
                exception = ex;
            }
        } while (numAttempts <= this.maxRetries);
        System.out.println("Try error : " + numAttempts);
        throw exception;
    }
}

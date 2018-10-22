package mooc.birdsky.springframework.aop.schema;

import mooc.birdsky.springframework.aop.schema.tracking.UsageTrack;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * date  : 2018.10.20
 * author: birdsky
 * 切面实现类 ，实现advice方法
 */
public class MoocAspect  {

    public void recordUsage(UsageTrack usageTracked){
        usageTracked.incrementUsecount();
        System.out.println("record process");
    }


    /**
     * before advice
     */
    public void aopbefore() {
        System.out.println("MoocAspect before");
    }

    /**
     * after advice
     */
    public void aopafterReturn() {
        System.out.println("MoocAspect after return");
    }

    /**
     * after throw
     */

    public void aopafterThrow()
    {
        System.out.println("MoocAspect throw");
    }

    /**
     * after final
     */
    public void aopafterFinal()
    {
        System.out.println("MoocAspect after final");
    }

    /**
     * round
     */
    public Object aopround(ProceedingJoinPoint pjp){
        Object obj = null;

        try {
            System.out.println("round before process");
            obj = pjp.proceed();
        }catch (Throwable e)
        {
            e.printStackTrace();
        }finally {
            System.out.println("round after process");
            return obj;
        }
    }

    public  Object initround(ProceedingJoinPoint pjp,String bizName,int times){
        Object obj = null;

        try {
            System.out.println("round before init process and bizName:"+bizName+" times:"+times);
            obj = pjp.proceed();
        }catch (Throwable e)
        {
            e.printStackTrace();
        }finally {
            System.out.println("round after init process");
            return obj;
        }
    }
}

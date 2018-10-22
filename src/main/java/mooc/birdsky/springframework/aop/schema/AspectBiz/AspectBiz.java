package mooc.birdsky.springframework.aop.schema.AspectBiz;

/**
 * date  : 2018.10.20
 * author: birdsky
 * 模拟业务类
 */
public class AspectBiz {
    private String bizName;
    private int times;

    public void biz()
    {
        System.out.println("AspectBiz biz ");
    }

    public void bizThrow(){
        //System.out.println("AspectBiz bizThrow");
        throw new RuntimeException();
    }

    public void init(String bizName, int times){
        this.bizName = bizName;
        this.times = times;
        System.out.println("bizName:"+this.bizName+ " try times:"+times);
    }
}

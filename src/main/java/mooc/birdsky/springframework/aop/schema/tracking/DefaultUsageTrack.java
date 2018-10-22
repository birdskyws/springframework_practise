package mooc.birdsky.springframework.aop.schema.tracking;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultUsageTrack implements UsageTrack{

    public static AtomicInteger count = new AtomicInteger(0);
    @Override
    public void incrementUsecount() {
        System.out.println("before :"+count.get());
        count.getAndIncrement();
        System.out.println("after :"+count.get());

    }

    public void printUsecount()
    {
        System.out.println(count.get());
    }
}

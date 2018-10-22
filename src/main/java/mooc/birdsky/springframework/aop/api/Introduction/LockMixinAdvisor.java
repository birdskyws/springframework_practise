package mooc.birdsky.springframework.aop.api.Introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class LockMixinAdvisor extends DefaultIntroductionAdvisor {
    public LockMixinAdvisor()
    {
        super(new LockMixin(),Lockable.class);
    }

}

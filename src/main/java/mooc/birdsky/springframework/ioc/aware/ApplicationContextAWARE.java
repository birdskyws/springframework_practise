package mooc.birdsky.springframework.ioc.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAWARE implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void printSelf()
    {
        ApplicationContextAWARE application = (ApplicationContextAWARE)this.applicationContext.getBean("applcationAWARE");
        System.out.println("application code :"+application.hashCode());
    }
}

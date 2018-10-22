package mooc.birdsky.springframework.ioc.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoocConfiguration {

    //@Bean(name="store")
    @Bean
    public Store stringStore()
    {
        return new StringStore();
    }
}

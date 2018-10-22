package mooc.birdsky.springframework.ioc.annotation.Configuration.generic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationGeneric {


    @Bean
    public TStore stringTStrore(){
        return new StringTStore();
    }

    @Bean
    public TStore integerTStrore()
    {
        return new IntegerTStore();
    }

}

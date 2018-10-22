package mooc.birdsky.springframework.ioc.annotation.Configuration.importresource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ImportResource(classpath:/config.xml)
 * config.xml是bean配置文件
 * <beans>
 * <context:property-placeholder location="classpath:/configure.xml">
 *     </context:property-placeholder>
 * </beans>
 *
 */
@Configuration
@ImportResource("classpath:/configure.xml")
public class StoreConfiguration {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${password}")
    private String url;

    @Bean
    public MyDriveManager driveManager(){
        return new MyDriveManager(username,password,url);
    }
}

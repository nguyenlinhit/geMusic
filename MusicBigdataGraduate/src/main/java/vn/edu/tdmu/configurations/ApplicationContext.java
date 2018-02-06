package vn.edu.tdmu.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import vn.edu.tdmu.common.CurrentTimeDateTimeService;
import vn.edu.tdmu.common.DateTimeService;
import vn.edu.tdmu.common.Profiles;
import vn.edu.tdmu.utils.WebInvocationPrivilegeEvaluatorUtil;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Configuration
@ComponentScan({"vn.edu.tdmu", "vn.edu.tdmu.common"})
@Import({WebMvcConfiguration.class, HibernateConfiguration.class, SecurityConfiguration.class})
public class ApplicationContext {
    @Autowired
    WebInvocationPrivilegeEvaluatorUtil wipe;


    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Configuration
    @Profile(Profiles.PROD)
    @PropertySource("classpath:application.properties")
    static class ProductionProperties {
    }

    @Bean
    @Profile(Profiles.PROD)
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }
}

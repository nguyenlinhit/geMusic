package vn.edu.tdmu;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

//import vn.edu.tdmu.configurations.HibernateConfiguration;
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration(exclude = {HibernateConfiguration.class})
//@ComponentScan("vn.edu.tdmu")
public class MusicBigdataGraduateApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MusicBigdataGraduateApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicBigdataGraduateApplication.class, args);
	}
}

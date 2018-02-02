package vn.edu.tdmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import vn.edu.tdmu.common.Profiles;
import vn.edu.tdmu.configurations.ApplicationContext;

import javax.servlet.*;
import java.util.EnumSet;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = {ApplicationContext.class})
@ComponentScan("vn.edu.tdmu")
public class MusicBigdataGraduateApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static final String CHARACTER_ENCODING_FILTER_ENCODING = "UTF-8";
    private static final String CHARACTER_ENCODING_FILTER_NAME = "characterEncoding";
    private static final String CHARACTER_ENCODING_FILTER_URL_PATTERN = "/*";

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    private static final String LOCATION = "E:/Upload/";
    private static final long MAX_FILE_SIZE = 10240000;
    private static final long MAX_REQUEST_SIZE = 102400000;
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MusicBigdataGraduateApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicBigdataGraduateApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationContext.class);

        configureDispatcherServlet(servletContext, rootContext);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        configureCharacterEncodingFilter(servletContext, dispatcherTypes);
        configureSpringSecurityFilter(servletContext, dispatcherTypes);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        servletContext.setInitParameter("spring.profiles.default", Profiles.PROD);
    }

    private void configureDispatcherServlet(ServletContext servletContext, WebApplicationContext rootContext) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);

        dispatcher.setMultipartConfig(new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
    }

    private void configureCharacterEncodingFilter(ServletContext servletContext, EnumSet<DispatcherType> dispatcherTypes) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CHARACTER_ENCODING_FILTER_ENCODING);
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter(CHARACTER_ENCODING_FILTER_NAME, characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, CHARACTER_ENCODING_FILTER_URL_PATTERN);
    }

    private void configureSpringSecurityFilter(ServletContext servletContext, EnumSet<DispatcherType> dispatcherTypes) {
        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}


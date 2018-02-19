package vn.edu.tdmu.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import vn.edu.tdmu.converters.*;

import java.util.List;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

    private ArtistConverter artistConverter;
    private GenreConverter genreConverter;
    private WeekConverter weekConverter;
    private RoleConverter roleConverter;
    private SongConverter songConverter;

    @Autowired
    public WebMvcConfiguration(ArtistConverter artistConverter, GenreConverter genreConverter, WeekConverter weekConverter, RoleConverter roleConverter, SongConverter songConverter) {
        this.artistConverter = artistConverter;
        this.genreConverter = genreConverter;
        this.weekConverter = weekConverter;
        this.roleConverter = roleConverter;
        this.songConverter = songConverter;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean
    TilesViewResolver viewResolver(){
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setViewClass(TilesView.class);
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("WEB-INF/titles/defs/general.xml", "WEB-INF/titles/defs/admin.xml", "WEB-INF/titles/defs/test.xml");
        return tilesConfigurer;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //objectMapper.registerModule(new JSR310Module());
        objectMapper.registerModule(new Hibernate5Module());
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        converters.add(converter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addConverter(artistConverter);
        registry.addConverter(genreConverter);
        registry.addConverter(weekConverter);
        registry.addConverter(roleConverter);
        registry.addConverter(songConverter);
    }
}
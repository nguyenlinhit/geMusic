package vn.edu.tdmu.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import vn.edu.tdmu.common.AjaxAuthenticationFailureHandler;
import vn.edu.tdmu.common.AjaxAuthenticationSuccessHandler;
import vn.edu.tdmu.common.CustomLogoutSuccessHandler;

import javax.sql.DataSource;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final AjaxAuthenticationSuccessHandler ajaxLoginSuccessHandler;
    private final AjaxAuthenticationFailureHandler ajaxLoginFailureHandler;
    private final CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public SecurityConfiguration(@Qualifier("dataSource") DataSource dataSource, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService, AjaxAuthenticationSuccessHandler ajaxLoginSuccessHandler, AjaxAuthenticationFailureHandler ajaxLoginFailureHandler, CustomLogoutSuccessHandler logoutSuccessHandler) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.ajaxLoginSuccessHandler = ajaxLoginSuccessHandler;
        this.ajaxLoginFailureHandler = ajaxLoginFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //.antMatchers("/login").access("isAnonymous()")
                .antMatchers("/admin**").access("hasRole('ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/login").successHandler(ajaxLoginSuccessHandler).failureHandler(ajaxLoginFailureHandler).permitAll()
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(864000)
                .and().logout().deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler)
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Spring Security ignores request to static resources such as CSS or JS files.
        web
                .ignoring()
                .antMatchers("/static/**")
                //.antMatchers(HttpMethod.POST, "/rest/**")
                .antMatchers(HttpMethod.POST, "/admin/song/*/AddArtist")
                .antMatchers(HttpMethod.POST, "/admin/song/*/RemoveArtist")
                .antMatchers(HttpMethod.POST, "/admin/playlist/*/AddSongs")
                .antMatchers(HttpMethod.POST, "/admin/playlist/*/UpdateSongPlaylist-*");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}

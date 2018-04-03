/*
package com.epam.mediaserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.epam.mediaserver")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;


    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       */
/* http.authorizeRequests()
            .and()
            .formLogin()
            .loginPage("/").permitAll()
            .loginProcessingUrl("/login")
            .successForwardUrl("/user/")
            .failureHandler(new FailureHandler())
            .and()
            .logout()
            .logoutUrl("/")
            .logoutSuccessUrl("/user/")
            .invalidateHttpSession(true)
            .and()
            .sessionManagement()
            .maximumSessions(-1)
            .sessionRegistry(sessionRegistry());*//*



//        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("*login")).and().authorizeRequests()
//            .antMatchers("/user").hasRole("USER").and().formLogin().defaultSuccessUrl("/user")
//            .loginPage("/login")
//            .and().logout().permitAll();
}
}
*/

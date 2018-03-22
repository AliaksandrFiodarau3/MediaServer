/*
package com.epam.mediaserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@ComponentScan(basePackages = "com.epam.mediaserver")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select user_login, user_password, enabled from t_user where username=?")
            .authoritiesByUsernameQuery("select user_login, r_role from users join role on u_role = r_id where username = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       */
/* http.formLogin().loginPage("/login").loginProcessingUrl("/appLogin").usernameParameter("user_login")
            .passwordParameter("user_password").defaultSuccessUrl("/manager").and().logout().logoutUrl("/appLogout")
            .logoutSuccessUrl("/login");*//*


        http
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("true")
            .antMatchers("/db/**").access("hasRole(true)")
            .anyRequest().authenticated()
            .and()
            .authorizeRequests()
            .antMatchers("/user/**").hasRole("false")
            .antMatchers("/db/**").access("hasRole(false)")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll();

      */
/*  http
            .logout()
            .logoutUrl("/my/logout")
            //.logoutSuccessUrl("/home")
            .logoutSuccessHandler(logoutSuccessHandler)
            .invalidateHttpSession(true)
            .addLogoutHandler(logoutHandler)
            .deleteCookies(cookieNamesToClear)
            .and();*//*

    }

}
*/

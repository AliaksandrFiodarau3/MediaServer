package com.epam.mediaserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.epam.mediaserver")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   /* @Resource(name = "userDetailService")
    private UserDetailsService userDetailsService;*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("User").password("Qwerty1234").roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

      /*  http.authorizeRequests()
            .anyRequest().authenticated()
            .antMatchers("user/**").access("hasRole('ROLE_USER')")
            .antMatchers("admin/**").access("hasRole('ROLE_ADMIN')")
            .and()
            .formLogin().loginPage("/login").failureUrl("/").defaultSuccessUrl("/user")
            .and()
            .logout().logoutUrl("/login").permitAll();*/


        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("USER")
            .and().formLogin().defaultSuccessUrl("/user").failureUrl("/login?error=true")
            .loginPage("/login").and().logout().permitAll();
    }
}

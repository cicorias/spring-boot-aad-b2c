package com.cedarlogic.webtier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/home")
            .permitAll()
            .antMatchers("/admin", "/h2_console/**")
            .permitAll()
            // .hasRole("ADMIN")
            // .anyRequest()
            // .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll();

        http.exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests()
    // .antMatchers("/", "/home").permitAll()
    // .antMatchers("/admin", "/h2_console/**").hasRole("ADMIN").anyRequest()
    // .authenticated()
    // .and()
    // .formLogin().loginPage("/login").permitAll()
    // .and()
    // .logout().permitAll();
    // http.exceptionHandling().accessDeniedPage("/403");
    // http.csrf().disable();
    // http.headers().frameOptions().disable();
    // }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
                .password("admin").roles("ADMIN");
    }
}
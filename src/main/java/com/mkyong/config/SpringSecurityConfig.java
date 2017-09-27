package com.mkyong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/instruktor/**").hasAnyRole("USER", "ADMIN", "MANAGER")
                .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()

                .withUser("jcichon").password("jcichon1a").roles("MANAGER")
                .and()
                .withUser("kskotniczny").password("kskotniczny2z").roles("USER")
                .and()
                .withUser("pszydlo").password("pszydlo4k").roles("USER")
                .and()
                .withUser("lkrason").password("lkrason4s").roles("USER")
                .and()
                .withUser("szef").password("szef").roles("ADMIN")
                .and()
                .withUser("kipias").password("kipias7x").roles("MANAGER");
    }
}
package com.muscletech.common;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/staff/login").permitAll()
                .antMatchers("/advertisement/**").permitAll()
                .antMatchers("/card/**").permitAll()
                .antMatchers("/data").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/staff/**").permitAll()
                .antMatchers("/venue/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/staff/login")
                .loginProcessingUrl("/staff/venue")
                .failureUrl("/staff/login")
                .permitAll()
                .and()
            .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/staff/manager");
        web.ignoring().antMatchers("/css/**", "/js/**");
        web.ignoring().antMatchers("/api/**","/swagger-ui/", "/webjars/**","/v2/api-docs","/swagger-resources","/swagger-resources/configuration/ui","/swagger-resources/configuration/security");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

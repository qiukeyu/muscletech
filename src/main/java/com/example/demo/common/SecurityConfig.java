package com.example.demo.common;

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
                .antMatchers("/staff/**").hasRole("STAFF")
                .antMatchers("/staff/manage").hasRole("MANAGER")
                .antMatchers("/user/login/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/staff/login")
                .loginProcessingUrl("/staff/venue")
                .failureUrl("/staff/login")
                .permitAll()
                .and()
            .csrf().disable();
        http.logout().logoutUrl("/staff/logout");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/**");
        web.ignoring().antMatchers("/staff/**");
        web.ignoring().antMatchers("/getUserInfo");
        web.ignoring().antMatchers("/css/**", "/js/**");
        web.ignoring().antMatchers("/swagger-ui");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

package com.jaksa.it355.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/kategorije/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/proizvodi/**").permitAll()
                .antMatchers("index.html").permitAll()
//                .antMatchers("/admin/kategorije/**").
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .permitAll()
                    .defaultSuccessUrl("/proizvodi/", true)
//                .failureHandler(authenticationFailureHandler())
                    .and()

                .logout()
                    .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/proizvodi/")
                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler(logoutSuccessHandler());
;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}
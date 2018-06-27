package com.netcracker.travelplanner.security.config;

import com.netcracker.travelplanner.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Advanced security configuration for BootSpringSecurity
 * Extends of {@link WebSecurityConfigurerAdapter} class
 * Аннотация EnableWebSecurity отключает все настройки SpringSecurity, но
 * благодаря наследованию от класса WebSecurityConfigurerAdapter, возможно использовать механизм
 * адаптеров, позволяющие не писать с нуля настройки.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //For GUESTS users
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/signUp","/js/**","/css/**","/img/**",
                        "/fonts/**","/navbar/**","/api/**").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/adduser", "/signUp", "/api/**").permitAll();

        // If no login, it will redirect to /login page.
        http.formLogin().loginPage("/signIn").defaultSuccessUrl("/users").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and().csrf().disable();

        //For ADMINS and USERS only
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER");

        // For ADMINS only.
        http.authorizeRequests().anyRequest().hasRole("ADMIN");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*аутентификация с зашифрованным паролем*/
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());

    }
}
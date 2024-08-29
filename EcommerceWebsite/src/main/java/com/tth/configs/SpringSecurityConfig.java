/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author tongh
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tth.controllers",
    "com.tth.repositories",
    "com.tth.services",
    "com.tth.components",})

@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dsbkju7j9",
                        "api_key", "982684178848551",
                        "api_secret", "veCzmGLh0g_vtPIedaVgNJlzoKQ",
                        "secure", true));
        return cloudinary;
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error=true");
        http.logout().logoutSuccessUrl("/");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");
        http.authorizeRequests().antMatchers("/manage-products")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/manage-categories")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/manage-brands")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/manage-users")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/stats")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/brands")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/products")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/categories")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/users")
                .access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/tagProducts")
                .access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/**/add")
//                .access("hasRole('ROLE_ADMIN')");
//        .antMatchers("/**/pay")
//                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        http.csrf().disable();
    }

}

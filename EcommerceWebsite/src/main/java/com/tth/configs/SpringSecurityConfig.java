/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.filters.CustomAccessDeniedHandler;
import com.tth.filters.RestAuthenticationEntryPoint;
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
        http.formLogin().loginPage("/login").usernameParameter("username")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true");
        http.logout().logoutSuccessUrl("/login");
        http.authorizeRequests().antMatchers("/manage-products")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
                .antMatchers("/products")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
                .antMatchers("/stats")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
                .antMatchers("/manage-categories")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/manage-brands")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/manage-users")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/brands")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/categories")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/users")
                .access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/tagProducts")
                .access("hasRole('ROLE_SUPER_ADMIN')"); //        .antMatchers("/").permitAll()
                //                .antMatchers("/**/add")
                //                .access("hasRole('ROLE_ADMIN')");
                //        .antMatchers("/**/pay")
                //                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        http.csrf().disable();
    }

}

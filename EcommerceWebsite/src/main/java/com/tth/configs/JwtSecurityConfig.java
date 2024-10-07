/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.configs;

import com.tth.filters.CustomAccessDeniedHandler;
import com.tth.filters.JwtAuthenticationTokenFilter;
import com.tth.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
    "com.tth.components",
    "com.tth.advice",
    "com.tth.validator"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;

    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/api/**");
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/products/").permitAll();
        http.authorizeRequests().antMatchers("/api/product/**").permitAll();
        http.authorizeRequests().antMatchers("/api/categories/").permitAll();
        http.authorizeRequests().antMatchers("/api/categories/**").permitAll();
        http.authorizeRequests().antMatchers("/api/brands/").permitAll();
        http.authorizeRequests().antMatchers("/api/brands/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users/update/**").permitAll();
        http.authorizeRequests().antMatchers("/api/provinces/").permitAll();
        http.authorizeRequests().antMatchers("/api/province/**").permitAll();
        http.authorizeRequests().antMatchers("/api/districts/").permitAll();
        http.authorizeRequests().antMatchers("/api/district/**").permitAll();
        http.authorizeRequests().antMatchers("/api/wards/").permitAll();
        http.authorizeRequests().antMatchers("/api/ward/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tags/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tagProducts/**").permitAll();
        http.authorizeRequests().antMatchers("/api/verifyOtp/**").permitAll();
        http.authorizeRequests().antMatchers("/api/verifyAccount/**").permitAll();
        http.authorizeRequests().antMatchers("/api/change-password/**").permitAll();
        http.authorizeRequests().antMatchers("/api/update-quantity-product/**").permitAll();
        http.authorizeRequests().antMatchers("/api/register/").permitAll();
        http.authorizeRequests().antMatchers("/api/current-user/").permitAll();
        http.authorizeRequests().antMatchers("/api/recently-viewed/**").permitAll();
        http.authorizeRequests().antMatchers("/api/cart/**").permitAll();
        http.authorizeRequests().antMatchers("/api/forgot-password/**").permitAll();
        http.authorizeRequests().antMatchers("/api/addresses/**").permitAll();

        //http.authorizeRequests().antMatchers("/api/tags/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                //                .antMatchers(HttpMethod.DELETE, "/api/products/**").permitAll()
                //                .antMatchers(HttpMethod.DELETE, "/api/categories/**").permitAll()
                //                .antMatchers(HttpMethod.DELETE, "/api/brands/**").permitAll()
                //
                //                .antMatchers(HttpMethod.POST, "/api/addresses/").hasAnyRole("CUSTOMER", "ADMIN", "SUPER_ADMIN") // Thêm địa chỉ
                //                .antMatchers(HttpMethod.GET, "/api/addresses/**").hasAnyRole("ROLE_CUSTOMER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN") // Lấy địa chỉ của người dùng
                //                .antMatchers(HttpMethod.PUT, "/api/addresses/{id}/").hasAnyRole("CUSTOMER", "ADMIN", "SUPER_ADMIN") // Cập nhật địa chỉ
                //                .antMatchers(HttpMethod.DELETE, "/api/addresses/delete/{id}/").hasAnyRole("ADMIN", "SUPER_ADMIN") // Xóa địa chỉ

                .antMatchers(HttpMethod.POST, "/api/users/update/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/current-user/").access("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/forgot-password/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/cart/**").access("hasRole('ROLE_CUSTOMER')")
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN')").antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}

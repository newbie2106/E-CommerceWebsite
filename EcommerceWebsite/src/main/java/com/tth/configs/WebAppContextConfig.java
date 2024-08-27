/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.configs;

import com.tth.formatters.RoleFormatter;
import com.tth.formatters.BrandFormatter;
import com.tth.formatters.CategoryFormatter;
import com.tth.formatters.DistrictFormatter;
import com.tth.formatters.ProductFormatter;
import com.tth.formatters.ProvinceFormatter;
import com.tth.formatters.TagFormatter;
import com.tth.formatters.WardFormatter;
import java.text.SimpleDateFormat;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author tongh
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tth.controllers",
    "com.tth.repositories",
    "com.tth.services",
    "com.tth.components",
    "com.tth.advice",
    "com.tth.validator"
})
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");

        registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/resources/assets/");

    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10000000);
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");
        return m;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter());
        registry.addFormatter(new BrandFormatter());
        registry.addFormatter(new RoleFormatter());
        registry.addFormatter(new ProvinceFormatter());
        registry.addFormatter(new DistrictFormatter());
        registry.addFormatter(new WardFormatter());
        registry.addFormatter(new ProductFormatter());
        registry.addFormatter(new TagFormatter());

    }
}

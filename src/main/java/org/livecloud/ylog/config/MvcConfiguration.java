package org.livecloud.ylog.config;

import java.util.Properties;

import org.livecloud.ylog.controller.DefaultController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {"org.livecloud.ylog"},
        includeFilters = {@ComponentScan.Filter(Controller.class)})
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Basic setup for JSP views.
     */
    @Bean
    public InternalResourceViewResolver configureInternalResourceViewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public CommonsMultipartResolver configureMultipartResolver() {
        CommonsMultipartResolver resolver =
                new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1048576000);
        resolver.setMaxInMemorySize(40960);
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Bean
    public SimpleUrlHandlerMapping configureSimpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping resolver =
                new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.put("/*/**", getDefaultController());
        resolver.setOrder(2147483647);
        resolver.setMappings(mappings);
        return resolver;
    }
    
    @Bean
    public DefaultController getDefaultController() {
        return new DefaultController();
    }

}

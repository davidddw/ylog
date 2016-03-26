package com.yunshan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

@Configuration
@Import({JettyConfiguration.class, SpringSecurityConfiguration.class, DataConfiguration.class})
@ComponentScan(basePackages = {"com.yunshan"},
        excludeFilters = {@ComponentScan.Filter(Controller.class),
                @ComponentScan.Filter(Configuration.class)})
public class RootConfiguration {

    /**
     * Allows access to properties. eg) @Value("${jetty.port}").
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * A default Metrics registry.
     */
    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    /**
     * A default Health registry (part of Metrics).
     */
    @Bean
    public HealthCheckRegistry healthCheckRegistry() {
        return new HealthCheckRegistry();
    }


}

package com.yunshan.main;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@Configuration
@ComponentScan("com.yunshan")
@MapperScan(basePackages = {"com.yunshan.mapper"})
@EnableTransactionManagement 
public class SpringJavaConfiguration {
    @Bean(autowire = Autowire.BY_TYPE)
    public DataSource dataSource() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setUrl("jdbc:mysql://10.33.37.28:20130/livecloud?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false&amp;maxReconnects=10");
        poolProperties.setUsername("cloud");
        poolProperties.setPassword("yunshan3302");
        poolProperties.setJmxEnabled(true);
        poolProperties.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        poolProperties.setRemoveAbandonedTimeout(60);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setLogAbandoned(false);
        poolProperties.setMinIdle(10);
        poolProperties.setMinEvictableIdleTimeMillis(30000);
        poolProperties.setMaxWait(10);
        poolProperties.setInitialSize(2);
        poolProperties.setMaxActive(10);
        poolProperties.setTimeBetweenEvictionRunsMillis(30000);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setValidationInterval(30000);
        poolProperties.setTestOnReturn(false);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestWhileIdle(true);
        poolProperties.setJmxEnabled(true);
        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(poolProperties);
        return dataSource;
    }

    @Bean(name="sqlSessionFactory" )
    public SqlSessionFactory getSqlSesssionFactory() throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        return bean.getObject();
    }
    
}

package org.livecloud.ylog.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@MapperScan(basePackages = {"org.livecloud.ylog.mapper"})
@PropertySource("classpath:jdbc.properties")
public class DataConfiguration {
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    Environment environment;
    
    @Bean(name = "datasource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getRequiredProperty("c3p0.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("c3p0.url"));
        dataSource.setUser(environment.getRequiredProperty("c3p0.user"));
        dataSource.setPassword(environment.getRequiredProperty("c3p0.password"));
        dataSource.setInitialPoolSize(environment.getRequiredProperty("c3p0.initialPoolSize", Integer.class));
        dataSource.setMaxPoolSize(environment.getRequiredProperty("c3p0.maxPoolSize", Integer.class));
        dataSource.setMinPoolSize(environment.getRequiredProperty("c3p0.minPoolSize", Integer.class));
        dataSource.setAcquireIncrement(environment.getRequiredProperty("c3p0.acquireIncrement", Integer.class));
        dataSource.setMaxStatements(environment.getRequiredProperty("c3p0.maxStatements", Integer.class));
        dataSource.setMaxIdleTime(environment.getRequiredProperty("c3p0.maxIdleTime", Integer.class));
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        sessionFactory.setTypeAliasesPackage("org.livecloud.ylog");
        sessionFactory.setTransactionFactory(springManagedTransactionFactory());
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException{
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SpringManagedTransactionFactory springManagedTransactionFactory() {
        return new SpringManagedTransactionFactory();
    }

}

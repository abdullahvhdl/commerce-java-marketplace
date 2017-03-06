package com.respodo.commerce.repository.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("com.respodo.commerce.repository.dao")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@PropertySource("classpath:application.properties")
public class JPAConfig {
	
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driverClassName";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.username";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";     
     
    @Resource
    private Environment env;
    
    @Bean(name="springSecurityAuditorAware")
    public SpringSecurityAuditorAware auditorAware(){
    	return new SpringSecurityAuditorAware();
    }
     
    
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts("development, production");
        return liquibase;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    	em.setDataSource(dataSource());
          em.setPackagesToScan(new String[] { "com.respodo.commerce.repository.domain" });
     
          JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
          em.setJpaVendorAdapter(vendorAdapter);
          em.setJpaProperties(additionalProperties());
     
          return em;
       }
     
       @Bean
       public DataSource dataSource(){
          DriverManagerDataSource dataSource = new DriverManagerDataSource();
          dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
          dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
          dataSource.setUsername( env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME) );
          dataSource.setPassword( env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD) );
          return dataSource;
       }
     
       @Bean
       public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
          JpaTransactionManager transactionManager = new JpaTransactionManager();
          transactionManager.setEntityManagerFactory(emf);
          return transactionManager;
       }
     
       @Bean
       public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
          return new PersistenceExceptionTranslationPostProcessor();
       }
     
       Properties additionalProperties() {
          Properties properties = new Properties();
          properties.setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
          properties.setProperty(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
          return properties;
       }
       


}
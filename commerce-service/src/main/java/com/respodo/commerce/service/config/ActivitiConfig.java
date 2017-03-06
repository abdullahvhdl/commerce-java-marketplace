package com.respodo.commerce.service.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.respodo.commerce.manager.impl.ActivitiGroupManagerFactory;
import com.respodo.commerce.manager.impl.ActivitiUserManagerFactory;

@Configuration
public class ActivitiConfig {
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private ActivitiGroupManagerFactory activitiGroupManagerFactory;
	@Autowired
	private ActivitiUserManagerFactory activitiUserManagerFactory;
	
	
	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration() {
		SpringProcessEngineConfiguration bean=new SpringProcessEngineConfiguration();
		try {
			bean.setDeploymentResources(appContext.getResources("classpath:processes/quickOrder.bar"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setDataSource(dataSource);
		bean.setTransactionManager(transactionManager);
		bean.setDatabaseSchemaUpdate("true");
		bean.setJobExecutorActivate(false);
		List<SessionFactory> sessionFactories=new ArrayList<SessionFactory>();
		sessionFactories.add(activitiGroupManagerFactory);
		sessionFactories.add(activitiUserManagerFactory);
		bean.setCustomSessionFactories(sessionFactories);
		bean.setMailServerHost("smtp.gmail.com");
		bean.setMailServerUsername("ibr.yasn@gmail.com");
		bean.setMailServerPassword("rjuwnfopnimnlrbv");
		bean.setMailServerPort(587);
		bean.setMailServerUseSSL(true);
		bean.setMailServerUseTLS(true);
		return bean;
	}
	
	@Bean
	public ProcessEngineFactoryBean processEngine(){
		ProcessEngineFactoryBean bean=new ProcessEngineFactoryBean();
		bean.setProcessEngineConfiguration(processEngineConfiguration());
		return bean;
	}
	
	@Bean
	public RepositoryService repositoryService(ProcessEngineFactoryBean pefb) throws Exception{
		return pefb.getObject().getRepositoryService();
	}
 
	@Bean
	public RuntimeService runtimeService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getRuntimeService();
	}
 
	@Bean
	public HistoryService historyService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getHistoryService();
	}
 
	@Bean
	public ManagementService managementService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getManagementService();
	}
 
	@Bean
	public IdentityService identityService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getIdentityService();
	}
 
	@Bean
	public FormService formService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getFormService();
	}
 
	@Bean
	public TaskService taskService(ProcessEngineFactoryBean pefb) throws Exception {
		return pefb.getObject().getTaskService();
	}

}

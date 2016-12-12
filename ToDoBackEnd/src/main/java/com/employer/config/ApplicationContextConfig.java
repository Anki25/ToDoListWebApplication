package com.employer.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.employer.dao.TasksDAO;
import com.employer.dao.TasksDAOImpl;
import com.employer.dao.UserInfoDAO;
import com.employer.dao.UserInfoDAOImpl;
import com.employer.model.Tasks;
import com.employer.model.UserInfo;

@Configuration
@ComponentScan("com.employer")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("ankita");
		dataSource.setPassword("anki25");
		return dataSource;
	}

	private Properties getHibernateProperties() {

		Properties connectionProperties = new Properties();

		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		// dataSource.setConnectionProperties(connectionProperties);
		return connectionProperties;
	}

	@Autowired // automatically bean is created n injected
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource); // sessionBuilder
																								// can
																								// b
																								// any
																								// other
																								// name
																								// too
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(UserInfo.class);
		sessionBuilder.addAnnotatedClass(Tasks.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory); // should
																											// be
																											// same
																											// name
																											// as
																											// bean
																											// name
																											// of
																											// session
																											// factory
		return transactionManager;
	}

	@Autowired
	@Bean(name = "userInfo")
	public UserInfo getUserInfo() {
		return new UserInfo();
	}

	@Autowired
	@Bean(name = "userInfoDAO")
	public UserInfoDAO getUserInfoDAO(SessionFactory sessionFactory) {
		return new UserInfoDAOImpl(sessionFactory);
	}


	@Autowired
	@Bean(name = "tasks")
	public Tasks getTasks() {
		return new Tasks();
	}

	@Autowired
	@Bean(name = "tasksDAO")
	public TasksDAO getTasksDAO(SessionFactory sessionFactory) {
		return new TasksDAOImpl(sessionFactory);
	}

	}

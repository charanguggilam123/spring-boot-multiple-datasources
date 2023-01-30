package com.gsc.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gsc.demo.domain.TODO;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"com.gsc.demo.todo.repo" },
		entityManagerFactoryRef = "todosEntityManagerFactory",
		transactionManagerRef = "todosTransactionManager")
public class TodoJpaConfiguration {

	@Bean(name = "todoDataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.todos")
	DataSource todoDS() {
		return DataSourceBuilder.create()
//		        .username("username")
//		        .password("")
//		        .url("jdbc:h2:mem:testdb")
//		        .driverClassName("")
				.build();

	}

	@Bean(name = "todosEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
			@Qualifier("todoDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		return builder.dataSource(dataSource).properties(props).packages(TODO.class).persistenceUnit("todos").build();
	}

	@Bean(name = "todosTransactionManager")
	@Primary
	public PlatformTransactionManager todosTransactionManager(
			@Qualifier("todosEntityManagerFactory") EntityManagerFactory todosEntityManagerFactory) {
		return new JpaTransactionManager(todosEntityManagerFactory);
	}

}

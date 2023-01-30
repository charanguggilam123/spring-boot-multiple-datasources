package com.gsc.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gsc.demo.domain.Client;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"com.gsc.demo.client.repo"},
		entityManagerFactoryRef = "clientEntityManagerFactory",
		transactionManagerRef = "clientTransactionManager")
public class ClientJpaConfiguration {

	@Bean(name = "clientDataSource")
	@ConfigurationProperties("spring.datasource.client")
	DataSource clientDS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean clientEntityManagerFactory(
			@Qualifier("clientDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		return builder.dataSource(dataSource).packages(Client.class).properties(props).persistenceUnit("clients")
				.build();
	}

	@Bean
	public PlatformTransactionManager clientTransactionManager(
			@Qualifier("clientEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
	}

}

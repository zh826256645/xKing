package xKing.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源 Config
 * @author 钟浩
 * @date 2016年11月16日
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("**.dao")
@PropertySource("classpath:jdbc.properties")
public class DataConfig {
	
	// 数据源
	@Bean
	public ComboPooledDataSource dataSource(Environment env) throws PropertyVetoException {
		ComboPooledDataSource pool = new ComboPooledDataSource();
		pool.setDriverClass(env.getProperty("jdbc.driver"));
		pool.setJdbcUrl(env.getProperty("jdbc.url"));
		pool.setUser(env.getProperty("jdbc.user"));
		pool.setPassword(env.getProperty("jdbc.password"));
		pool.setInitialPoolSize(5);
		return pool;
	}
	
	// 容器管理类型的 JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			// 传入数据源和 JPA 适配器
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		emfb.setJpaProperties(jpaProperties);
		emfb.setPackagesToScan("**.domain");
		return emfb;
	}
	
	// 配置 JPA 适配器 bean
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		// 设置使用的数据库
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	

	// 事物管理 bean
	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
}

package xKing.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class DataConfig {
	
	// 数据源
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource pool = new ComboPooledDataSource();
		pool.setDriverClass("com.mysql.jdbc.Driver");
		pool.setJdbcUrl("jdbc:mysql://localhost:3306/xking");
		pool.setUser("root");
		pool.setPassword("123");
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

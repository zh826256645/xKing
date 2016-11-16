package xKing.config;

import java.util.Properties;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * 邮件服务 config
 * 
 * @author 钟浩
 * @date 2016年11月8日
 *
 */

@Configuration
@ComponentScan("xKing.mail")
@PropertySource("classpath:mail.properties")
public class MailConfig {
	
	// mail 发送
	@Bean
	public JavaMailSenderImpl mailSender(Environment env) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mailserver.host"));
		mailSender.setUsername(env.getProperty("mailserver.username"));
		mailSender.setPassword(env.getProperty("mailserver.password"));
		return mailSender;
	}
	
	// mail 模板
	@Bean
	public VelocityEngineFactoryBean velocityEngine() {
		VelocityEngineFactoryBean velocityEngine = 
				new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class", 
				ClasspathResourceLoader.class.getName());
		props.setProperty("input.encoding", "UTF-8");
		props.setProperty("output.encoding", "UTF-8");
		velocityEngine.setVelocityProperties(props);
		return velocityEngine;
	}

}

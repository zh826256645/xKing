package xKing.config;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import xKing.interceptor.UploadInterceptor;


/**
 * WebConfig
 * 
 * @author 钟浩
 * @date 2016年10月12日
 *
 */

// 配置文件
@Configuration
// Spring MVC 配置
@EnableWebMvc
@EnableSpringDataWebSupport
// 启动自动扫描
@ComponentScan("xKing.*.web")
public class WebConfig extends WebMvcConfigurerAdapter{

	// 配置 JSP 视图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		// 加前缀
		resolver.setPrefix("/WEB-INF/views/");
		// 加后缀
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	// 配置静态资源的处理
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 不使用 DispatcherServlet 处理
		// 交给 Servlet 容器中默认的 Servlet 处理
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UploadInterceptor()).
			addPathPatterns("/setting/picture").
				addPathPatterns("/branch/new");
	}
	
	
	// 国际化信息
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		return new StandardServletMultipartResolver();
	}
}

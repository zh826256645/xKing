package xKing.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 注册 DispatcherServlet
 * 
 * @author 钟浩
 * @date 2016年10月12日
 *
 */
public class XKingWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	// 设置映射路径
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	// 指定配置类：RootConfig
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {DataConfig.class, RootConfig.class, SecurityConfig.class, MailConfig.class};
	}

	// 指定配置类：WebConfig
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addFilter("encodingFilter", new CharacterEncodingFilter("UTF-8", true))
			.addMappingForUrlPatterns(null, false, "/*");
	}
}

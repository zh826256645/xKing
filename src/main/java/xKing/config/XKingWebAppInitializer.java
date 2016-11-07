package xKing.config;

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
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	// 指定配置类：WebConfig
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}
}

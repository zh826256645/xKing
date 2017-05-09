package xKing.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

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
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(
				// 设置临时保存目录，最大上传图片大小
				new MultipartConfigElement("/home/zhonghao/temp",2*1024*1024, 4*1024*1024, 0));
	}
}

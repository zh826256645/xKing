package xKing.user.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xKing.user.domain.User;
import xKing.user.service.UserService;

/**
 * Hander
 * 用户成功登录后处理
 * 
 * @author 钟浩
 * @date 2016年11月13日
 *
 */
public class UserSucceedLoginHander extends SavedRequestAwareAuthenticationSuccessHandler {

	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		userService = webApplicationContext.getBean(UserService.class);
		
		String username = authentication.getName();
		User user = userService.getUserByUsername(username);
		if(user.getState() == 0) {
			request.getSession().setAttribute("userNotActivateError", "账号未激活，请到点击注册邮箱中的激活链接！");
			authentication.setAuthenticated(false);
		} else {
			request.getSession().removeAttribute("userNotActivateError");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

package xKing.user.web;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xKing.user.domain.User;
import xKing.user.exception.UserNotActivateException;
import xKing.user.service.UserService;

/**
 * Filter
 * 拦截未激活用户
 * 
 * @author 钟浩
 * @date 2016年11月13日
 *
 */
public class UserMustActivateFilter extends AbstractAuthenticationProcessingFilter {

	private UserService userService;


	public UserMustActivateFilter(String defaultFilterProcessesUrl, String failureUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 当处于用户验证时
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			
			// 获取 UserService
			ServletContext context = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
			userService = webApplicationContext.getBean(UserService.class);
			
			UserDetails userDetails  = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String username = userDetails.getUsername();
			
			if(username != null && userService.getUserByUsername(username) != null) {		
				User user = userService.getUserByUsername(username);
				// 用户账号状态未激活
				if(user.getState() == 0 ) {
					request.getSession().setAttribute("userNotActivateError", "账号未激活，请到点击注册邮箱中的激活链接！");
					unsuccessfulAuthentication(request, response, new UserNotActivateException("用户未激活"));
					return;
				}
			}
		}
		super.doFilter(request, response, chain);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return null;
	}
	
}

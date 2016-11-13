package xKing.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Hander
 * 用户成功登录后处理
 * 
 * @author 钟浩
 * @date 2016年11月13日
 *
 */
public class UserSucceedLoginHander extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("userNotActivateError");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

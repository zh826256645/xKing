package xKing.user.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户未激活异常
 * 
 * @author 钟浩
 * @date 2016年11月13日
 *
 */
public class UserNotActivateException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public UserNotActivateException(String msg) {
		super(msg);
	}

}

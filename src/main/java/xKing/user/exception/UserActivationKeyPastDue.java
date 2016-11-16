package xKing.user.exception;

/**
 * 用户激活码过期异常
 * 
 * @author 钟浩
 * @date 2016年11月15日
 *
 */
public class UserActivationKeyPastDue extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserActivationKeyPastDue() {
		super();
	}

	public UserActivationKeyPastDue(String msg) {
		super(msg);
	}
}

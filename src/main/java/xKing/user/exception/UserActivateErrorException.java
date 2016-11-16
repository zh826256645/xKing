package xKing.user.exception;

/**
 * 用户激活码异常
 * 
 * @author 钟浩
 * @date 2016年11月15日
 *
 */

public class UserActivateErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserActivateErrorException() {
		super();
	}
	
	public UserActivateErrorException(String msg) {
		super(msg);
	}

}

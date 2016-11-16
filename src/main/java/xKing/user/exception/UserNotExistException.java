package xKing.user.exception;

/**
 * 用户不存在异常
 * 
 * @author 钟浩
 * @date 2016年11月15日
 *
 */
public class UserNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserNotExistException() {
		super();
	}
	
	public UserNotExistException(String msg) {
		super(msg);
	}
}

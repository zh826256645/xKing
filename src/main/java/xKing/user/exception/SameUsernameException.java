package xKing.user.exception;

/**
 * 用户名重复异常
 * 
 * @author 钟浩
 * @date 2016年11月9日
 *
 */
public class SameUsernameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SameUsernameException() {
	}
	
	public SameUsernameException(String info) {
		super(info);
	}

}

package xKing.exception;

/**
 * 业务提示信息异常 
 * @author zhonghao
 *
 */

public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MessageException() {
		super();
	}

	public MessageException(String message) {
		super(message);
	}
}

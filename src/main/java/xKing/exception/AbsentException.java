package xKing.exception;

/**
 * 不存在异常
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
public class AbsentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public AbsentException() {
		super();
	}

	public AbsentException(String message) {
		super(message);
	}
}

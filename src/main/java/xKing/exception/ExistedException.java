package xKing.exception;

/**
 * 以存在异常
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
public class ExistedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExistedException() {
		super();
	}

	public ExistedException(String message) {
		super(message);
	}
}

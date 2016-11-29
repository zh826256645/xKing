package xKing.exception;

/**
 * 名字相同异常
 * 
 * @author 钟浩
 * @date 2016年11月28日
 *
 */
public class SameNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SameNameException() {
		super();
	}

	public SameNameException(String message) {
		super(message);
	}
}

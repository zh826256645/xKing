package xKing.exception;

public class FaultyOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FaultyOperationException(){
		super();
	}
	
	public FaultyOperationException(String msg){
		super(msg);
	}
}

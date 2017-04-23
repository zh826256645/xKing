package xKing.exception;

public class PermissionDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public PermissionDeniedException() {
		super();
	}

	public PermissionDeniedException(String message) {
		super(message);
	}
}

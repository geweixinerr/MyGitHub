package exception;

/**
 * added by gewx 2019.8.9 并发异常
 **/
public final class ConcurrentException extends RuntimeException {

	private static final long serialVersionUID = -2481884182369728336L;

	public ConcurrentException() {

	}

	public ConcurrentException(String message) {
		super(message);
	}

	public ConcurrentException(Throwable cause) {
		super(cause);
	}

	public ConcurrentException(String message, Throwable cause) {
		super(message, cause);
	}
}

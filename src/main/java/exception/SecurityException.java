package exception;

/**
 * added by gewx 2018.1.2 加密/解密自定义异常
 * **/
public final class SecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1550435532175466795L;

	public SecurityException() {

	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

}

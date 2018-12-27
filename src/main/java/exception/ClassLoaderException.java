package exception;

/**
 * added by gewx 2018.12.27 类加载异常.
 * **/
public final class ClassLoaderException extends RuntimeException {

	/**
	 */
	private static final long serialVersionUID = 964160725199419937L;

	public ClassLoaderException() {

	}

	public ClassLoaderException(String message) {
		super(message);
	}

	public ClassLoaderException(Throwable cause) {
		super(cause);
	}

	public ClassLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

}

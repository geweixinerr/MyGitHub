package exception;

/**
 * 业务异常类
 * 
 * @author gewx
 **/
public final class BusinessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4236266714060887509L;

	public BusinessRuntimeException() {
		super();
	}

	public BusinessRuntimeException(String msg) {
		super(msg);
	}
}

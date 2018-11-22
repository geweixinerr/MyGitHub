package exception;

/**
 * 转换异常
 */

public class ConvertException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ConvertException() {
	      super();
    }


    public ConvertException(String s) {
	     super(s);
    }
}

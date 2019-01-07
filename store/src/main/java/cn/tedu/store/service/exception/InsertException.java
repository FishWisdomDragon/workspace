package cn.tedu.store.service.exception;

public class InsertException extends ServiceException {

	/**
	 * 数据插入异常
	 */
	private static final long serialVersionUID = -3169386579545714789L;

	

	public InsertException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsertException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

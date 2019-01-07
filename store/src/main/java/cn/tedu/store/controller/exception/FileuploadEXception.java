package cn.tedu.store.controller.exception;

public class FileuploadEXception extends RequestException{

	/**
	 * 文件上传异常
	 */
	private static final long serialVersionUID = -3336662690215079975L;

	public FileuploadEXception() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileuploadEXception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileuploadEXception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileuploadEXception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileuploadEXception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

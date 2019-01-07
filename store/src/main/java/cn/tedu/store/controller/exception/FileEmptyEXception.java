package cn.tedu.store.controller.exception;

public class FileEmptyEXception extends FileuploadEXception {

	/**
	 * 文件上传空异常
	 */
	private static final long serialVersionUID = 1010580044824890546L;

	public FileEmptyEXception() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEmptyEXception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyEXception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyEXception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyEXception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

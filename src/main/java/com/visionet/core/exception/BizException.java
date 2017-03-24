package com.visionet.core.exception;


public class BizException extends RuntimeException{
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4658334004006964422L;

	public BizException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause,
						boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizException(String result, String message) {
		super(message);
		this.result = result;
		// TODO Auto-generated constructor stub
	}

	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}

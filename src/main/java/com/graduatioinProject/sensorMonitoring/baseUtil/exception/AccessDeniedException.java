package com.graduatioinProject.sensorMonitoring.baseUtil.exception;

public class AccessDeniedException extends RuntimeException {
	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(ExMessage message) {
		super(message.getMessage());
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
	}

	protected AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

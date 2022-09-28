package com.graduatioinProject.sensorMonitoring.baseUtil.exception;

public class BussinessException extends RuntimeException {
	public BussinessException() {
		super();
	}

	public BussinessException(ExMessage exMessage) {
		super(exMessage.getMessage());
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}
}

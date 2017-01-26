package CalculatorDemo.controllers.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private HttpStatus code;
	private String message;

	public ErrorMessage(HttpStatus notFound, String message) {
		this.code = notFound;
		this.message = message;
	}

	public HttpStatus getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

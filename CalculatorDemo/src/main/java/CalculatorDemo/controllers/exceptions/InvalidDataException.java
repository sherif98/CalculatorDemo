package CalculatorDemo.controllers.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class InvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StringBuilder message;

	public InvalidDataException(List<ObjectError> allErrors) {
		message = new StringBuilder();
		allErrors.stream().forEach(e -> message.append(e.getDefaultMessage()));
	}

	public String getMessage() {
		return message.toString();
	}

}

package CalculatorDemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import CalculatorDemo.controllers.exceptions.ErrorMessage;

@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(Throwable.class)
	public ErrorMessage applicationCrashed() {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Try Again Later!");
	}
}

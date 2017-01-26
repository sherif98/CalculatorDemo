package CalculatorDemo.controllers.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;

	public UserAlreadyExistsException(String name) {
		this.userName = name;
	}

	public String getUserName() {
		return userName;
	}

}

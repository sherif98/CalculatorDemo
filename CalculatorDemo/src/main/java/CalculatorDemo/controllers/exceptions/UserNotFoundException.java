package CalculatorDemo.controllers.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userId;

	public UserNotFoundException(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

}

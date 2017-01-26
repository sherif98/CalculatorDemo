package CalculatorDemo.controllers.exceptions;

public class ComputationNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long computationId;

	public ComputationNotFoundException(long computationId) {
		this.computationId = computationId;
	}

	public long getComputationId() {
		return computationId;
	}
}

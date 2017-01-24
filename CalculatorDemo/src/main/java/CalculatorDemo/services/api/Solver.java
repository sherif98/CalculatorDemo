package CalculatorDemo.services.api;

public interface Solver {
	/**
	 * Evaluate an arithmetic expression and return the the result
	 * 
	 * @param expression
	 *            expression to evaluate
	 * @return the result of the expression
	 */
	public String compute(String expression);
}

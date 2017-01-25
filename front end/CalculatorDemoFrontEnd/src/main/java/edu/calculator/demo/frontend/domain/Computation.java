package edu.calculator.demo.frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Computation {
	private String expression;
	private String result;

	@Override
	public String toString() {
		return "Computation [expression=" + expression + ", result=" + result + "]";
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}

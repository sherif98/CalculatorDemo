package CalculatorDemo.services.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CalculatorDemo.services.api.Solver;

public class SolverImpl implements Solver {

	private static final Logger log = LoggerFactory.getLogger(SolverImpl.class);

	@Override
	public String compute(String expression) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String result = "";
		try {
			log.info("start evaluating the expression....");
			log.info("the expression is: " + expression);
			result = engine.eval(expression).toString();
			log.info("the result is: " + result);
			log.info("finished evaluating the expression successfully.");
		} catch (ScriptException e) {
			log.info("failed to evaluate the expression, Exception is" + e);
		}
		return result;
	}

}

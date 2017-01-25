package CalculatorDemo.services.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import CalculatorDemo.services.api.Solver;

@Service
public class SolverImpl implements Solver {

	@Override
	public String compute(String expression) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String result = "";
		try {
			result = engine.eval(expression).toString();
		} catch (ScriptException e) {
		}
		return result;
	}

}

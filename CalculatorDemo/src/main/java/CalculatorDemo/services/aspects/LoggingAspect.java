package CalculatorDemo.services.aspects;

import javax.script.ScriptException;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import CalculatorDemo.services.impl.SolverImpl;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger log = LoggerFactory.getLogger(SolverImpl.class);
	private static final String computePointCut = "execution(* CalculatorDemo.services.api.Solver.compute(String))";

	@Pointcut(computePointCut + " && args(equation)")
	public void computeLogger(String equation) {
	}

	@Before("computeLogger(equation)")
	public void printLogsBeforeCompute(String equation) {
		log.info("start evaluating the expression....");
		log.info("the expression is: " + equation);
	}

	@AfterReturning(pointcut = computePointCut, returning = "answer")
	public void printLogsAfterSuccess(String answer) {
		log.info("the result is: " + answer);
		log.info("finished evaluating the expression successfully.");
	}

	@AfterThrowing(pointcut = computePointCut, throwing = "exc")
	public void printLogsAfterFailure(ScriptException exc) {
		log.info("failed to evaluate the expression, Exception is" + exc);
	}
}

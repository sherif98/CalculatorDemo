package CalculatorDemo.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import CalculatorDemo.services.api.Solver;
import CalculatorDemo.services.impl.SolverImpl;

public class SolverImplTest {

	private Solver solver;

	@Before
	public void init() {
		solver = new SolverImpl();
	}

	@Test
	public void testOne() {
		String result = solver.compute("1+2");
		assertEquals(result, String.valueOf(1+2));
	}

	@Test
	public void testTwo() {
		String result = solver.compute("1+2*434");
		double numericalResult = Double.parseDouble(result);
		assertEquals(0, Double.compare(numericalResult, 1+2*434));
	}
}

package CalculatorDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CalculatorDemo.services.api.Solver;
import CalculatorDemo.services.impl.StringEncoderDecoderService;

@RestController
public class CalculatorController {

	@Autowired
	private Solver solver;
	@Autowired
	private StringEncoderDecoderService encoderDecoder;

	@RequestMapping(value = "/calc", method = RequestMethod.GET)
	public String solve(@RequestParam(value = "equation") String encodedEquation) {
		String equation = encoderDecoder.decode(encodedEquation);
		String answer = solver.compute(equation);
		return answer;
	}
}

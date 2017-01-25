package CalculatorDemo.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Computation Not Found", code = HttpStatus.NOT_FOUND)
public class ComputationNotFoundException extends RuntimeException {

}

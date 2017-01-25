package edu.calculator.demo.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import edu.calculator.demo.frontend.domain.Computation;
import edu.calculator.demo.frontend.domain.User;

@SpringBootApplication
public class CalculatorDemoFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorDemoFrontEndApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject("http://localhost:8080/history/user/Tarek", User.class);
		System.out.println(user);
		Computation computation = new Computation();
		computation.setExpression("4 + 5");
		computation.setResult("9");
		restTemplate.put("http://localhost:8080/history/update/" + user.getId(), computation);
		User updatedUser = restTemplate.getForObject("http://localhost:8080/history/user/Tarek", User.class);
		System.out.println("updated user: " + updatedUser);
		
		
	}
}

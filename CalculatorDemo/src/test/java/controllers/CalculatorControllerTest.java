package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import CalculatorDemo.controllers.CalculatorController;

public class CalculatorControllerTest {
	@Test
	public void testHomePage() throws Exception {
		CalculatorController controller = new CalculatorController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/calc?equation=MSArIDE="));
	}
}

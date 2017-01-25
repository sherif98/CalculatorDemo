package CalculatorDemo.services.api;

import org.springframework.stereotype.Service;

import CalculatorDemo.controllers.dto.UserLoginRegisterationInfo;
import CalculatorDemo.services.pojos.LoginFeedback;


public interface AuthenticationService {
	public LoginFeedback login(UserLoginRegisterationInfo loginInfo);
}

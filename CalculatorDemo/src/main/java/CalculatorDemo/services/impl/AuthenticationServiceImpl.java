package CalculatorDemo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CalculatorDemo.controllers.dto.UserLoginRegisterationInfo;
import CalculatorDemo.dao.api.UserDAO;
import CalculatorDemo.dao.entities.UserEntry;
import CalculatorDemo.services.api.AuthenticationService;
import CalculatorDemo.services.pojos.LoginFeedback;
import CalculatorDemo.services.pojos.LoginStatus;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserDAO userDAO;
	// @Autowired
	// private LoginFeedback feedback;

	@Override
	public LoginFeedback login(UserLoginRegisterationInfo loginInfo) {
		LoginFeedback feedback = new LoginFeedback();
		List<UserEntry> userEntry = userDAO.findByName(loginInfo.getName());
		if (userEntry == null || userEntry.size() == 0) {
			feedback.setLoginStatus(LoginStatus.USER_NOT_FOUND);
			return feedback;
		}
		UserEntry user = userEntry.get(0);
		if (!user.getPassword().equals(loginInfo.getPassword())) {
			feedback.setLoginStatus(LoginStatus.PASSWORD_INCORRECT);
			return feedback;
		}
		feedback.setLoginStatus(LoginStatus.SUCCESSFULL_LOGIN);
		feedback.setUserId((int) user.getId());
		return feedback;
	}
}

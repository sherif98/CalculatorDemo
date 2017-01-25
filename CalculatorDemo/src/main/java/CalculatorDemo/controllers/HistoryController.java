package CalculatorDemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CalculatorDemo.controllers.exceptions.UserNotFoundException;
import CalculatorDemo.dao.api.UserDAO;
import CalculatorDemo.dao.entities.UserEntry;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	@ResponseBody
	public UserEntry getUserByName(@PathVariable("name") String userName) {

		List<UserEntry> user = userDAO.findByName(userName);
		if (user == null || user.size() == 0) {
			throw new UserNotFoundException();
		}
		return user.get(0);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void addUser(@RequestBody UserEntry user) {
		userDAO.saveUser(user);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundHandler() {
		return "user not found";
	}
}

package CalculatorDemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CalculatorDemo.controllers.dto.Computation;
import CalculatorDemo.controllers.dto.UserLoginRegisterationInfo;
import CalculatorDemo.controllers.exceptions.ComputationNotFoundException;
import CalculatorDemo.controllers.exceptions.InvalidDataException;
import CalculatorDemo.controllers.exceptions.UserExistsException;
import CalculatorDemo.controllers.exceptions.UserNotFoundException;
import CalculatorDemo.dao.api.ComputationRepository;
import CalculatorDemo.dao.api.UserDAO;
import CalculatorDemo.dao.entities.ComputationEntry;
import CalculatorDemo.dao.entities.UserEntry;
import CalculatorDemo.services.api.AuthenticationService;
import CalculatorDemo.services.pojos.LoginFeedback;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ComputationRepository computationRepo;

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginFeedback login(@RequestBody UserLoginRegisterationInfo loginInfo) {
		LoginFeedback feedback = authenticationService.login(loginInfo);
		return feedback;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public UserEntry getUserById(@PathVariable long id) {
		UserEntry user = getUser(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	// @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	// public UserEntry getUserByName(@PathVariable("name") String userName) {
	// UserEntry user = getUser(userName);
	// if (user == null) {
	// throw new UserNotFoundException();
	// }
	// return user;
	// }

	@RequestMapping(value = "/user/{id}/computations", method = RequestMethod.GET)
	public List<ComputationEntry> getAllUserComputations(@PathVariable long id,
			@RequestParam(value = "max", required = false) Long max) {
		System.out.println(max);
		UserEntry user = getUser(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		List<ComputationEntry> computations = user.getComputations();
		if (max != null) {
			int numOfComputations = (int) Long.min(max, computations.size());
			computations = computations.subList(0, numOfComputations);
		}
		return computations;
	}

	// @RequestMapping(value = "/user/{id}/computations", method =
	// RequestMethod.GET)
	// public List<ComputationEntry> getKUserComputations(@PathVariable long id,
	// @RequestParam("max") long max) {
	// UserEntry user = getUser(id);
	// List<ComputationEntry> computations = user.getComputations();
	// int numOfComputations = (int) Long.min(max, computations.size());
	// System.out.println(numOfComputations);
	// return computations.subList(0, numOfComputations - 1);
	// }

	@RequestMapping(value = "/user/{userId}/computations/{computationId}", method = RequestMethod.GET)
	public ComputationEntry getUserComputationById(@PathVariable long userId, @PathVariable long computationId) {
		try {
			ComputationEntry computation = computationRepo.getOne(computationId);
			return computation;
		} catch (Exception e) {
			throw new ComputationNotFoundException();
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void addUser(@RequestBody @Valid UserLoginRegisterationInfo newUser, Errors errors) {
		if (errors.hasErrors()) {
			throw new InvalidDataException();
		}
		if (userExists(newUser)) {
			throw new UserExistsException();
		}
		saveNewUser(newUser);
	}

	// @RequestMapping(value = "/user", method = RequestMethod.POST)
	// public void addUser(@RequestBody UserEntry user) {
	// UserEntry userToAdd = new UserEntry(user.getName(), user.getPassword());
	// userToAdd.setComputations(new HashSet<>());
	// for (ComputationEntry computation : user.getComputations()) {
	// ComputationEntry computationToAdd = new
	// ComputationEntry(computation.getExpression(),
	// computation.getResult(), userToAdd);
	// userToAdd.getComputations().add(computationToAdd);
	// }
	// userDAO.saveUser(user);
	// }

	@RequestMapping(value = "user/{id}/computations/", method = RequestMethod.POST)
	public void addComputation(@PathVariable long id, @RequestBody Computation computation) {
		UserEntry userToUpdate = getUser(id);
		if (userToUpdate == null) {
			throw new UserNotFoundException();
		}
		ComputationEntry computationToAdd = new ComputationEntry(computation, userToUpdate);
		userToUpdate.getComputations().add(computationToAdd);
		userDAO.saveUser(userToUpdate);
	}

	// @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	// public UserEntry update(@PathVariable("id") long id, @RequestBody
	// ComputationEntry computation) {
	// UserEntry userToUpdate = userDAO.findUser(id);
	// ComputationEntry computationToAdd = new
	// ComputationEntry(computation.getExpression(), computation.getResult(),
	// userToUpdate);
	// userToUpdate.getComputations().add(computationToAdd);
	// return userDAO.saveUser(userToUpdate);
	// }

	private void saveNewUser(UserLoginRegisterationInfo newUser) {
		UserEntry user = new UserEntry(newUser);
		userDAO.saveUser(user);
	}

	private UserEntry getUser(long id) {
		UserEntry user = userDAO.findUser(id);
		return user;
	}

	private UserEntry getUser(String name) {
		List<UserEntry> user = userDAO.findByName(name);
		if (user == null || user.size() == 0) {
			return null;
		}
		return user.get(0);
	}

	private boolean userExists(UserLoginRegisterationInfo newUser) {
		UserEntry user = getUser(newUser.getName());
		return user != null;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundHandler() {
		return "user not found";
	}
}

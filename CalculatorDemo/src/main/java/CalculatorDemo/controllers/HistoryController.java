package CalculatorDemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import CalculatorDemo.controllers.dto.Computation;
import CalculatorDemo.controllers.dto.UserLoginRegisterationInfo;
import CalculatorDemo.controllers.exceptions.ComputationNotFoundException;
import CalculatorDemo.controllers.exceptions.ErrorMessage;
import CalculatorDemo.controllers.exceptions.InvalidDataException;
import CalculatorDemo.controllers.exceptions.UserAlreadyExistsException;
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
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@RequestMapping(value = "/user/{id}/computations", method = RequestMethod.GET)
	public List<ComputationEntry> getAllUserComputations(@PathVariable long id,
			@RequestParam(value = "max", required = false) Long max) {
		System.out.println(max);
		UserEntry user = getUser(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		List<ComputationEntry> computations = user.getComputations();
		if (max != null) {
			int numOfComputations = (int) Long.min(max, computations.size());
			computations = computations.subList(0, numOfComputations);
		}
		return computations;
	}

	// TODO
	// request crashes (Internal Server Error)
	@RequestMapping(value = "/user/{userId}/computations/{computationId}", method = RequestMethod.GET)
	public ComputationEntry getUserComputationById(@PathVariable long userId, @PathVariable long computationId) {
		try {
			ComputationEntry computation = computationRepo.getOne(computationId);
			return computation;
		} catch (Exception e) {
			throw new ComputationNotFoundException(computationId);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserEntry addUser(@RequestBody @Valid UserLoginRegisterationInfo newUser, Errors errors) {
		if (errors.hasErrors()) {
			throw new InvalidDataException(errors.getAllErrors());
		}
		if (userExists(newUser)) {
			throw new UserAlreadyExistsException(newUser.getName());
		}
		UserEntry user = new UserEntry(newUser);
		userDAO.saveUser(user);
		return user;
	}

	@RequestMapping(value = "user/{id}/computations/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ComputationEntry addComputation(@PathVariable long id, @RequestBody Computation computation) {
		UserEntry userToUpdate = getUser(id);
		if (userToUpdate == null) {
			throw new UserNotFoundException(id);
		}
		ComputationEntry computationToAdd = new ComputationEntry(computation, userToUpdate);
		userToUpdate.getComputations().add(computationToAdd);
		userDAO.saveUser(userToUpdate);
		// TODO
		// returning the computationToAdd object always have id 0
		return computationToAdd;
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
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFound(UserNotFoundException exc) {
		return new ErrorMessage(HttpStatus.NOT_FOUND, "User [ " + exc.getUserId() + " ] not found");
	}

	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage invalidCredentials(InvalidDataException exc) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST, exc.getMessage());
	}

	@ExceptionHandler(ComputationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage computationNotFound(ComputationNotFoundException exc) {
		return new ErrorMessage(HttpStatus.NOT_FOUND, "User [ " + exc.getComputationId() + " ] not found");
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorMessage duplicateUser(UserAlreadyExistsException exc) {
		return new ErrorMessage(HttpStatus.CONFLICT, "User [ " + exc.getUserName() + " ] already exists");
	}

}

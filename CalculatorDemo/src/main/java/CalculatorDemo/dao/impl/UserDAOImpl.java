package CalculatorDemo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CalculatorDemo.dao.api.UserDAO;
import CalculatorDemo.dao.api.UserEntryRepository;
import CalculatorDemo.dao.entities.UserEntry;

@Service
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserEntryRepository repo;

	@Override
	public UserEntry findUser(Long id) {
		return repo.findOne(id);
	}

	@Override
	public UserEntry saveUser(UserEntry user) {
		return repo.saveAndFlush(user);
	}

	@Override
	public void saveUsers(UserEntry... users) {
		for (UserEntry user : users) {
			repo.save(user);
		}
		repo.flush();
	}

	@Override
	public void removeUser(Long id) {
		repo.delete(id);
	}

	@Override
	public List<UserEntry> findAll() {
		return repo.findAll();
	}

	@Override
	public List<UserEntry> findByName(String name) {
		return repo.findByName(name);
	}

}

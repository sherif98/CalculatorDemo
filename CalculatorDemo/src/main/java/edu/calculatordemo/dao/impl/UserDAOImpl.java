package edu.calculatordemo.dao.impl;

import edu.calculatordemo.dao.api.UserDAO;
import edu.calculatordemo.dao.entities.User;

public class UserDAOImpl extends GenericDAO<User, Integer> implements UserDAO {

	@Override
	public User findUser(int id) {
		return super.find(id);
	}

	@Override
	public void saveUser(User user) {
		super.persist(user);
	}

	@Override
	public void updateUser(User user) {
		super.merge(user);
	}

	@Override
	public void removeUser(User user) {
		super.remove(user);
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}

package edu.calculatordemo.dao.api;

import edu.calculatordemo.dao.entities.User;

public interface UserDAO {
	public User findUser(int id);

	public void saveUser(User user);

	public void updateUser(User user);
	
	public void removeUser(User user);
}

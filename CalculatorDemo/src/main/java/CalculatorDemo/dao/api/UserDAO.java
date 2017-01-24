package CalculatorDemo.dao.api;

import java.util.List;

import CalculatorDemo.dao.entities.UserEntry;

public interface UserDAO {
	/**
	 * Get A user from the DB using its id
	 * 
	 * @param id
	 * @return User entity from the DB
	 */
	public UserEntry findUser(Long id);

	/**
	 * Save a user to the DB, if the user already exists it will update it
	 * 
	 * @param user
	 *            user entity to save in the DB
	 */
	public void saveUser(UserEntry user);

	/**
	 * Saves variables number of users to the DB
	 * 
	 * @param users
	 *            users to save in the DB
	 */
	public void saveUsers(UserEntry... users);

	/**
	 * Get all the users from the DB
	 * 
	 * @return list of all the users from the DB
	 */
	public List<UserEntry> findAll();

	/**
	 * Delete a user from the DB
	 * 
	 * @param id
	 *            id of the user to delete from the DB
	 */
	public void removeUser(Long id);

	/**
	 * Get all the users with the same name
	 * 
	 * @param name
	 *            name to search for in the DB
	 * @return list of all the users with the same name
	 */
	public List<UserEntry> findByName(String name);
}

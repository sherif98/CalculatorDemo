package edu.calculatordemo.dao.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private int id;
	private String userName;
	private String password;
	private List<Computation> computations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Computation> getComputations() {
		return computations;
	}

	public void setComputations(List<Computation> computations) {
		this.computations = computations;
	}

	public void addComputation(Computation computation) {
		computations.add(computation);
	}
}

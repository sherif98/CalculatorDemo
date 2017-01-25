package CalculatorDemo.dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import CalculatorDemo.controllers.dto.UserLoginRegisterationInfo;

@Entity
@Table(name = "user_entry")
public class UserEntry {
	private long id;
	private String name;
	private String password;
	private List<ComputationEntry> computations;

	public UserEntry() {

	}

	public UserEntry(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public UserEntry(UserLoginRegisterationInfo newUser) {
		this.name = newUser.getName();
		this.password = newUser.getPassword();
		computations = new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "userEntry", cascade = CascadeType.ALL)
	public List<ComputationEntry> getComputations() {
		return computations;
	}

	public void setComputations(List<ComputationEntry> computations) {
		this.computations = computations;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEntry [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", computations=");
		builder.append(computations);
		builder.append("]");
		return builder.toString();
	}

}
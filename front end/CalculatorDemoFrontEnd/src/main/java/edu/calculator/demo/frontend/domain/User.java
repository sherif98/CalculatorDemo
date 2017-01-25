package edu.calculator.demo.frontend.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String name;
	private String password;
	private Set<Computation> computations;

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", computations=" + computations + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Computation> getComputations() {
		return computations;
	}

	public void setComputations(Set<Computation> computations) {
		this.computations = computations;
	}

}

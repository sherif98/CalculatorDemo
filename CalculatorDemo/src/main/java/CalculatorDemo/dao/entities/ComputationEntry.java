package CalculatorDemo.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import CalculatorDemo.controllers.dto.Computation;

@Entity
public class ComputationEntry {
	private int id;
	private String expression;
	private String result;
	@JsonIgnore
	private UserEntry userEntry;

	public ComputationEntry() {
	}

	public ComputationEntry(String name) {
		this.expression = name;
	}

	public ComputationEntry(String expression, String result, UserEntry userEntry) {
		this.expression = expression;
		this.result = result;
		this.userEntry = userEntry;
	}

	public ComputationEntry(Computation computation, UserEntry userEntry) {
		this.expression = computation.getExpression();
		this.result = computation.getResult();
		this.userEntry = userEntry;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@ManyToOne
	@JoinColumn(name = "user_entry_id")
	public UserEntry getUserEntry() {
		return userEntry;
	}

	public void setUserEntry(UserEntry userEntry) {
		this.userEntry = userEntry;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComputationEntry [id=");
		builder.append(id);
		builder.append(", expression=");
		builder.append(expression);
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}

}
package edu.calculatordemo.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T, ID extends Serializable> {

	@PersistenceContext
	protected EntityManager em;

	public abstract Class<T> getEntityClass();

	public T find(ID id) {
		return em.find(this.getEntityClass(), id);
	}

	public void persist(T entity) {
		em.persist(entity);
	}

	public T merge(T entity) {
		return em.merge(entity);
	}

	public void remove(T entity) {
		em.remove(entity);
	}
}

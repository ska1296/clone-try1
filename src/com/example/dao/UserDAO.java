package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.example.core.Users;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDAO extends AbstractDAO<Users> {

	public UserDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Users> getAll() {
		return (List<Users>) currentSession().createCriteria(Users.class).list();
	}

	public Users findById(String userDao) {
		return currentSession().get(Users.class, userDao);
	}

	public Users findByUserName(String userDao) {
		Query query = currentSession().createQuery("from Users where username = :username");
		query.setParameter("username", userDao);
		return query.list() == null || query.list().isEmpty() ? null : (Users) query.list().get(0);
	}

	public void delete(Users person) {
		currentSession().delete(person);
	}

	public void update(Users person) {
		currentSession().saveOrUpdate(person);
	}

	public Users insert(Users person) {
		return persist(person);
	}
}

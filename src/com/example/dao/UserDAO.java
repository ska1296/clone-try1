package com.example.dao;

import java.util.List;

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

    public Users findById(int id) {
    	System.out.println("id -> " + id);
        return currentSession().get(Users.class, id);
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

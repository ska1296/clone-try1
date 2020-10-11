package com.example.dao;

import java.util.UUID;

import org.hibernate.SessionFactory;

import com.example.core.Auth;

import io.dropwizard.hibernate.AbstractDAO;

public class AuthDAO extends AbstractDAO<Auth> {

	public AuthDAO(SessionFactory factory) {
		super(factory);
	}

	public UUID findUserByAuthToken(String authToken) {
		Auth x = currentSession().get(Auth.class, authToken);
		return x == null ? null : x.getUuid();
	}

	public String deleteAuthToken(String authToken) {
		try {
			currentSession().delete(currentSession().get(Auth.class, authToken));
		} catch (Exception e) {
			return "No logged in";
		}
		return "Logged out";
	}

	public void insert(Auth authDetail) {
		currentSession().save(authDetail);
	}
}

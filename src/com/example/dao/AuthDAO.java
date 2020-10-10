package com.example.dao;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.SessionFactory;

import com.example.core.Auth;

import io.dropwizard.hibernate.AbstractDAO;

public class AuthDAO extends AbstractDAO<Auth> {

	public AuthDAO(SessionFactory factory) {
		super(factory);
	}

	public @NotNull UUID findByAuthToken(String authToken) {
		return currentSession().get(Auth.class, authToken).getUuid();
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

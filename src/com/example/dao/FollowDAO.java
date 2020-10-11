package com.example.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import com.example.core.Follows;

import io.dropwizard.hibernate.AbstractDAO;

public class FollowDAO extends AbstractDAO<Follows> {

	public FollowDAO(SessionFactory factory) {
		super(factory);
	}

	public List<UUID> findByUuid(String uuid) {
		Criteria x = currentSession().createCriteria(Follows.class, uuid);
		return x == null ? null : (List<UUID>)  x.list();
	}

	public void insert(Follows followsDetail) {
		persist(followsDetail);
	}
}

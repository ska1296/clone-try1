package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.example.core.Likes;

import io.dropwizard.hibernate.AbstractDAO;

public class LikesDAO extends AbstractDAO<Likes> {

	public LikesDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Likes> getAll() {
		return (List<Likes>) currentSession().createCriteria(Likes.class).list();
	}

	public Likes findByUserName(String uuid) {
		Query query = currentSession().createQuery("from post where uuid = :uuid");
		query.setParameter("uuid", uuid);
		return query.list() == null || query.list().isEmpty() ? null : (Likes) query.list().get(0);
	}

	public void insert(Likes likedPost) {
		persist(likedPost);
		currentSession().flush();
		currentSession().clear();
	}
}

package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.example.core.Post;

import io.dropwizard.hibernate.AbstractDAO;

public class PostDAO extends AbstractDAO<Post> {

	public PostDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Post> getAll() {
		return (List<Post>) currentSession().createCriteria(Post.class).list();
	}

	public Post findByUserName(String uuid) {
		Query query = currentSession().createQuery("from post where uuid = :uuid");
		query.setParameter("uuid", uuid);
		return query.list() == null || query.list().isEmpty() ? null : (Post) query.list().get(0);
	}

	public void insert(Post post) {
		currentSession().save(post);
	}
}

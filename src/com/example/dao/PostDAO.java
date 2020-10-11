package com.example.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.example.core.Post;

import io.dropwizard.hibernate.AbstractDAO;

public class PostDAO extends AbstractDAO<Post> {

	public PostDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Post> findAllByUserName(UUID userUuid) {
		Query query = currentSession().createQuery("from Post where UUID = :UUID");
		query.setParameter("UUID", userUuid);
		return query.list() == null || query.list().isEmpty() ? null : (List<Post>) query.list();
	}

	public Post findByUserName(UUID uuid) {
		Query query = currentSession().createQuery("from Post where uuid = :uuid");
		query.setParameter("uuid", uuid);
		return query.list() == null || query.list().isEmpty() ? null : (Post) query.list().get(0);
	}

	public void insert(Post post) {
		currentSession().save(post);
	}

	public List<Post> findPosts(UUID currUser) {
		
		Query query =  currentSession().createQuery("from Post p where p.postId in (select t2.postId from Follows t1 join Post t2 on t1.followsUuid=t2.user where t1.followerUuid = :followed_uuid)"
				+ "or p.postId in (select t2.postId from Likes t1 join Post t2 on t1.post=t2.postId where t1.user = :likedBy)");
		query.setParameter("followed_uuid", currUser);
		query.setParameter("likedBy", currUser);
		return query.list() == null || query.list().isEmpty() ? null : (List<Post>) query.list();
		
	}
}

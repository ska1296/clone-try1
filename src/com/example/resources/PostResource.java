package com.example.resources;

import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.core.Post;
import com.example.dao.AuthDAO;
import com.example.dao.PostDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/post")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PostResource {

	PostDAO postDao;
	AuthDAO authDao;

	public PostResource(PostDAO postDao, AuthDAO authDao) {
		this.postDao = postDao;
		this.authDao = authDao;
	}

	
	@POST
	@Path("/{create}")
	@UnitOfWork
	public String create(@HeaderParam("token") String authString, @Valid Post post) {
		
		UUID uuid = authDao.findByAuthToken(authString);
		if(uuid == null)
			return "Sign in!";
		
		if (post.getPostData().length() > 140)
			return "Post too long.";
		
		post.setUser(uuid);
		postDao.insert(post);
		
		return "Post added!";
	}
	
}

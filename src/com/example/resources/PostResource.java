package com.example.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.core.Likes;
import com.example.core.Post;
import com.example.core.Users;
import com.example.dao.AuthDAO;
import com.example.dao.LikesDAO;
import com.example.dao.PostDAO;
import com.example.dao.UserDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/post")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PostResource {

	UserDAO userDao;
	PostDAO postDao;
	AuthDAO authDao;
	LikesDAO likesDao;

	public PostResource(PostDAO postDao, AuthDAO authDao, UserDAO userDao, LikesDAO likesDao) {
		this.postDao = postDao;
		this.authDao = authDao;
		this.userDao = userDao;
		this.likesDao = likesDao;
	}

	@POST
	@Path("/{create}")
	@UnitOfWork
	public String create(@HeaderParam("token") String authString, @Valid Post post) {

		UUID uuid = authDao.findUserByAuthToken(authString);
		if (uuid == null)
			return "Sign in!";

		if (post.getPostData().isEmpty())
			return "Recieved an empty post.";
		
		if (post.getPostData().length() > 140)
			return "Post too long.";

		post.setUser(uuid);
		postDao.insert(post);

		return "Post added!";
	}

	@GET
	@Path("/{like}/{all}")
	@UnitOfWork
	public String likeAll(@HeaderParam("token") String authString, @QueryParam("user") String userName) {

		UUID uuid = authDao.findUserByAuthToken(authString);
		if (uuid == null)
			return "Sign in!";

		Users user = userDao.findByUserName(userName);
		if (user == null)
			return "No user found";
		List<Post> allPosts = postDao.findAllByUserName(user.getUuid());
		Likes likedPost = new Likes();
		likedPost.setUser(uuid);
		for (Post eachPost : allPosts) {
			likedPost.setPost(eachPost.getPostId());
			likesDao.insert(likedPost);
		}

		return "Liked all!";

	}

	@POST
	@Path("/{like}/{current}")
	@UnitOfWork
	public String likeCurrent(@HeaderParam("token") String authString, @Valid Post post) {

		UUID uuid = authDao.findUserByAuthToken(authString);
		if (uuid == null)
			return "Sign in!";

		Likes likedPost = new Likes();
		likedPost.setUser(uuid);
		likedPost.setPost(post.getPostId());

		likesDao.insert(likedPost);

		return "Post liked!";
	}
	
	@GET
	@Path("/{view}/{user}/{all}")
	@UnitOfWork
	public List<Post> viewFollowed(@HeaderParam("token") String authString) {

		UUID uuid = authDao.findUserByAuthToken(authString);
		if (uuid == null)
			return new ArrayList<>();
		
		return postDao.findPosts(uuid);
	}
}

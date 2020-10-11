package com.example.resources;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.auth.Hash;
import com.example.core.Auth;
import com.example.core.Follows;
import com.example.core.SignIn;
import com.example.core.Users;
import com.example.dao.AuthDAO;
import com.example.dao.FollowDAO;
import com.example.dao.UserDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/user")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UserResource {

	UserDAO userDao;
	AuthDAO authDao;
	FollowDAO followsDao;

	public UserResource(UserDAO userDao, AuthDAO authDao, FollowDAO followsDao) {
		this.userDao = userDao;
		this.authDao = authDao;
		this.followsDao = followsDao;
	}

	@POST
	@Path("/{signup}")
	@UnitOfWork
	public String signup(@Valid Users user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setPassword(Hash.generateStrongPasswordHash(user.getPassword()));
		if (userDao.findByUserName(user.getUserName()) == null) {
			userDao.insert(user);
			return "User added!";
		}
		return "User is there.";
	}

	@POST
	@Path("/{signin}/{userName}")
	@UnitOfWork
	public String singin(@PathParam("userName") String userName, @Valid SignIn signInuser)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String authToken = "";
		Users matchedUser = userDao.findByUserName(signInuser.getUserName());
		if (matchedUser != null && Hash.validatePassword(signInuser.getPassword(), matchedUser.getPassword())) {
			authToken = Hash.generateStrongPasswordHash(matchedUser.getPassword()).replaceAll("\\s+", "");
			Auth authDetail = new Auth();
			authDetail.setAuthToken(authToken);
			authDetail.setUuid(matchedUser.getUuid());
			authDao.insert(authDetail);
			return authToken;
		}

		return "Invalid Credentials";

	}

	@GET
	@Path("/{follow}/{user}")
	@UnitOfWork
	public String followUser(@HeaderParam("token") String authString, @QueryParam("user") String userName) {

		UUID followerUuid = authDao.findUserByAuthToken(authString);
		if (followerUuid == null)
			return "Sign in!";

		if (userName == null || userName.isEmpty())
			return "No user mentioned!";

		Users followsUser = userDao.findByUserName(userName);
		if (followsUser == null)
			return "No such user exists!";

		if (followerUuid.equals(followsUser.getUuid()))
			return "Cannot follow self!";

		UUID followsUuid = followsUser.getUuid();
		Follows newFollow = new Follows();
		newFollow.setFollowerId(followerUuid);
		newFollow.setFollowsUuid(followsUuid);
		followsDao.insert(newFollow);

		return "Followed";
	}

	@GET
	@Path("/{find}")
	@UnitOfWork
	public String findUser(@QueryParam("user") String userName) {
		Users user = userDao.findByUserName(userName);
		if (user == null)
			return "No user found";
		return user.getUserName();
	}

	@DELETE
	@Path("/{signout}")
	@UnitOfWork
	public Response signout(@HeaderParam("token") String authString) {
		return Response.status(200).entity(authDao.deleteAuthToken(authString)).build();
	}

}

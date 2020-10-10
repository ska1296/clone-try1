package com.example.resources;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

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
import com.example.core.SignIn;
import com.example.core.Users;
import com.example.dao.AuthDAO;
import com.example.dao.UserDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/user")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UserResource {

	UserDAO userDao;
	AuthDAO authDao;

	public UserResource(UserDAO userDao, AuthDAO authDao) {
		this.userDao = userDao;
		this.authDao = authDao;
	}

	@GET
	@UnitOfWork
	public List<Users> getAll() {
		return userDao.getAll();
	}

	@GET
	@Path("/{find}")
	@UnitOfWork
	public String get(@QueryParam("user") String userName) {
		Users user = userDao.findByUserName(userName);
		if (user == null)
			return "No user found";
		return user.getUserName();
	}

	@POST
	@Path("/{signup}")
	@UnitOfWork
	public String add(@Valid Users user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setPassword(Hash.generateStorngPasswordHash(user.getPassword()));
		if (userDao.findByUserName(user.getUserName()) == null) {
			userDao.insert(user);
			return "Created! " + user.toString();
		}
		return "User is there.";
	}

	@POST
	@Path("/{signin}/{userName}")
	@UnitOfWork
	public String singin(@PathParam("userName") String userName, @Valid SignIn user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String authToken = "";
		Users x = userDao.findByUserName(user.getUserName());
		if (Hash.validatePassword(user.getPassword(), x.getPassword())) {
			authToken = Hash.generateStorngPasswordHash(x.getPassword());
			Auth authDetail = new Auth();
			authDetail.setAuthToken(authToken);
			authDetail.setUuid(x.getUuid());
			authDao.insert(authDetail);
			return authToken;
		}

		return "Invalid Credentials";

	}

	@DELETE
	@Path("/{singout}")
	@UnitOfWork
	public Response delete(@HeaderParam("token") String authString) {
		return Response.status(200).entity(authDao.deleteAuthToken(authString)).build();
	}

}

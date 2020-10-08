package com.example.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.core.Users;
import com.example.dao.UserDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/user")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {

	UserDAO registerDAO;

    public UserResource(UserDAO personDAO) {
        this.registerDAO = personDAO;
    }
    
    @GET
    @UnitOfWork
    public List<Users> getAll(){
        return registerDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Users get(@PathParam("id") Integer id){
        return registerDAO.findById(id);
    }

    @POST
    @UnitOfWork
    public Users add(@Valid Users user) {
    	Users newPerson = registerDAO.insert(user);
    	System.out.println(user.toString());
        return newPerson;
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Users update(@PathParam("id") Integer id, @Valid Users user) {
        user = user.setId(id);
        registerDAO.update(user);

        return user;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Integer id) {
        registerDAO.delete(registerDAO.findById(id));
    }
}

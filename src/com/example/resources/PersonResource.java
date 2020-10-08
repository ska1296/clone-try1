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

import com.example.core.Person;
import com.example.dao.PersonDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    PersonDAO personDAO;
    /*private final String template;
	private final String defaultName;
	private final AtomicLong counter;*/

    public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
       /* this.template = "person";
		this.defaultName = "sup, person";
		this.counter = new AtomicLong();*/
        
    }
    
   /* @GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.orElse(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}*/

    @GET
    @UnitOfWork
    public List<Person> getAll(){
        return personDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Person get(@PathParam("id") Integer id){
        return personDAO.findById(id);
    }

    @POST
    @UnitOfWork
    public Person add(@Valid Person person) {
        Person newPerson = personDAO.insert(person);

        return newPerson;
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Person update(@PathParam("id") Integer id, @Valid Person person) {
        person = person.setId(id);
        personDAO.update(person);

        return person;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Integer id) {
        personDAO.delete(personDAO.findById(id));
    }
}

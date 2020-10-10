package com.example;

import com.example.core.Auth;
import com.example.core.Person;
import com.example.core.Post;
import com.example.core.Users;
import com.example.dao.AuthDAO;
import com.example.dao.PersonDAO;
import com.example.dao.PostDAO;
import com.example.dao.UserDAO;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.resources.PersonResource;
import com.example.resources.PostResource;
import com.example.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ExampleApplication extends Application<ExampleConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    private final HibernateBundle<ExampleConfiguration> hibernate = new HibernateBundle<ExampleConfiguration>(Person.class, Users.class, 
    		Auth.class, Post.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ExampleConfiguration configuration) {
            return configuration.getDatabaseAppDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "dropwizard-hibernate";
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
    
    @Override
    public void run(ExampleConfiguration configuration, Environment environment) throws ClassNotFoundException {

    	final PersonDAO dao = new PersonDAO(hibernate.getSessionFactory());
        environment.jersey().register(new PersonResource(dao));
        
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
		environment.jersey().register(resource);
		
		final AuthDAO auth = new AuthDAO(hibernate.getSessionFactory());
		final PostDAO post = new PostDAO(hibernate.getSessionFactory());
		final UserDAO user = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(user, auth));
        environment.jersey().register(new PostResource(post, auth));
    }
}
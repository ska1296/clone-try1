package com.example;

import com.example.core.Auth;
import com.example.core.Follows;
import com.example.core.Likes;
import com.example.core.Post;
import com.example.core.Users;
import com.example.dao.AuthDAO;
import com.example.dao.FollowDAO;
import com.example.dao.LikesDAO;
import com.example.dao.PostDAO;
import com.example.dao.UserDAO;
import com.example.resources.PostResource;
import com.example.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TwitterCloneApplication extends Application<TwitterCloneConfiguration> {

    public static void main(String[] args) throws Exception {
        new TwitterCloneApplication().run(args);
    }

    private final HibernateBundle<TwitterCloneConfiguration> hibernate = new HibernateBundle<TwitterCloneConfiguration>(Users.class, 
    		Auth.class, Post.class, Follows.class, Likes.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TwitterCloneConfiguration configuration) {
            return configuration.getDatabaseAppDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "dropwizard-hibernate";
    }

    @Override
    public void initialize(Bootstrap<TwitterCloneConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
    
    @Override
    public void run(TwitterCloneConfiguration configuration, Environment environment) throws ClassNotFoundException {

		final FollowDAO follow = new FollowDAO(hibernate.getSessionFactory());
		final AuthDAO auth = new AuthDAO(hibernate.getSessionFactory());
		final PostDAO post = new PostDAO(hibernate.getSessionFactory());
		final UserDAO user = new UserDAO(hibernate.getSessionFactory());
		final LikesDAO likes = new LikesDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(user, auth, follow));
        environment.jersey().register(new PostResource(post, auth, user, likes));
    }
}
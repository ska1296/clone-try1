package com.example.core;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "LIKES", uniqueConstraints=@UniqueConstraint(columnNames= {"USER_UUID","POST_UUID"}))
public class Likes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "USER_UUID", nullable = false)
    @NotNull
    @JsonProperty
    private UUID user;

    @Id
    @Column(name = "POST_UUID", nullable = false)
    @NotNull
    @JsonProperty
    private UUID post;

	public UUID getUser() {
		return user;
	}

	public void setUser(UUID user) {
		this.user = user;
	}

	public UUID getPost() {
		return post;
	}

	public void setPost(UUID post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Likes {user=" + user + ", post=" + post + "}";
	}

}

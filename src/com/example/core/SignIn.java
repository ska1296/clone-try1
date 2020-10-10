package com.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER")
public class SignIn {

	@Id
	@Column(name = "USERNAME", nullable = false)
	@NotNull
	@JsonProperty
	private String userName;

	@Column(name = "PASSWORD", length = 100, nullable = false)
	@NotNull
	@JsonProperty
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignIn {userName=" + userName + ", password=" + password + "}";
	}
	
}

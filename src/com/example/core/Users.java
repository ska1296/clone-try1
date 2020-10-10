package com.example.core;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@JsonProperty
	@Column(name = "USERNAME", length = 255, nullable = false)
	private String userName;

	@Id
	@GeneratedValue
	@Column(name = "UUID", nullable = false)
	private UUID uuid;

	@Column(name = "PASSWORD", length = 255, nullable = false)
	@NotNull
	@JsonProperty
	private String password;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public Users setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users {uuid=" + uuid + ", userName=" + userName + ", password=" + password + "}";
	}

}

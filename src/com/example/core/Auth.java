package com.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTH")
public class Auth {

	@Column(name = "UUID", nullable = false)
	@NotNull
	@JsonProperty
	private UUID uuid;

	@Id
	@Column(name = "AUTH_TOKEN", length = 255, nullable = false)
	@NotNull
	@JsonProperty
	private String authToken;

	public @NotNull UUID getUuid() {
		return uuid;
	}

	public Auth setUuid(UUID uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public String toString() {
		return "Auth {uuid=" + uuid + ", authToken=" + authToken + "}";
	}
	
}

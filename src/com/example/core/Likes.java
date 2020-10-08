package com.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LIKES")
public class Likes {

    @Id
    @Column(name = "UUID_USER", nullable = false)
    @NotNull
    @JsonProperty
    private Integer id;

    @Column(name = "UUID_POST", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String followsUuid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFollowsUuid() {
		return followsUuid;
	}

	public void setFollowsUuid(String followsUuid) {
		this.followsUuid = followsUuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followsUuid == null) ? 0 : followsUuid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Likes other = (Likes) obj;
		if (followsUuid == null) {
			if (other.followsUuid != null)
				return false;
		} else if (!followsUuid.equals(other.followsUuid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Likes {id=" + id + ", followsUuid=" + followsUuid + "}";
	}
    
}

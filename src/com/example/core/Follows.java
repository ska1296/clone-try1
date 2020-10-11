package com.example.core;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FOLLOWS")
public class Follows {

    @Id
    @Column(name = "FOLLOWER_UUID", nullable = false)
    @NotNull
    private UUID followerUuid;

    @Column(name = "FOLLOWS_UUID", length = 100, nullable = false)
    @NotNull
    private UUID followsUuid;

	public UUID getFollowerId() {
		return followerUuid;
	}

	public void setFollowerId(UUID followerUuid) {
		this.followerUuid = followerUuid;
	}

	public UUID getFollowsUuid() {
		return followsUuid;
	}

	public void setFollowsUuid(UUID followsUuid) {
		this.followsUuid = followsUuid;
	}

	@Override
	public String toString() {
		return "Follows {followerUuid=" + followerUuid + ", followsUuid=" + followsUuid + "}";
	}

}

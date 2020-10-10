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
@Table(name = "POST")
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "POST_DATA", length = 140, nullable = false)
	@NotNull
	@JsonProperty
	private String postData;

	@Id
	@Column(name = "UUID", length = 255, nullable = false)
	private UUID user;

	@Column(name = "CREATETIME", length = 100, nullable = false)
	@NotNull
	@JsonProperty
	private String createTime;

	@Id
	@GeneratedValue
	@Column(name = "POST_UUID", length = 140, nullable = false)
	private UUID postId;

	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public UUID getUser() {
		return user;
	}

	public void setUser(UUID user) {
		this.user = user;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public UUID getPostId() {
		return postId;
	}

	public void setPostId(UUID postId) {
		this.postId = postId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Post {postData=" + postData + ", user=" + user + ", createTime=" + createTime + ", postId=" + postId
				+ "}";
	}

}

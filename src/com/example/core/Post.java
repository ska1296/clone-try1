package com.example.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "POST")
public class Post {

	//@Id
	@Column(name = "POST", nullable = false)
	@NotNull
	@JsonProperty
	private String postData;

	@Column(name = "UUID", length = 100, nullable = false)
	@NotNull
	@JsonProperty
	private String user;

	@Column(name = "CREATETIME", length = 100, nullable = false)
	@NotNull
	@JsonProperty
	private String createTime;
	
	@Column(name = "POST_UUID", length = 100, nullable = false)
	@NotNull
	@JsonProperty
	private String postId;

	public String getPost() {
		return postData;
	}

	public Post setPost(String postData) {
		this.postData = postData;
		return this;
	}

	public String getUser() {
		return user;
	}

	public Post setUser(String user) {
		this.user = user;
		return this;
	}

	public String getCreateTime() {
		return createTime;
	}

	public Post setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "Post {postData=" + postData + ", user=" + user + ", createTime=" + createTime + ", postId=" + postId
				+ "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((postData == null) ? 0 : postData.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Post other = (Post) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (postData == null) {
			if (other.postData != null)
				return false;
		} else if (!postData.equals(other.postData))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}

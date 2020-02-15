package com.coins.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "role_users")
public class RoleUser implements Serializable {
	@Column(name = "role_id")
	private Integer role_id;
	@Column(name = "user_id")
	private Integer user_id;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public RoleUser() {
		super();
	}

	public RoleUser(Integer role_id, Integer user_id) {
		super();
		this.role_id = role_id;
		this.user_id = user_id;
	}

}

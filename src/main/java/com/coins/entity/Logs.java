package com.coins.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Logs implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "admin_id")
	private Integer adminId;
	@Column(name = "user")
	private String user;
	@Column(name = "method")
	private String method;
	@Column(name = "url")
	private String url;
	@Column(name = "data")
	private String data;
	@Column(name = "created_at")
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Logs(Integer adminId, String user, String method, String url, String data, Date createdAt) {
		super();
		this.adminId = adminId;
		this.user = user;
		this.method = method;
		this.url = url;
		this.data = data;
		this.createdAt = createdAt;
	}

	public Logs() {
		super();
	}

}

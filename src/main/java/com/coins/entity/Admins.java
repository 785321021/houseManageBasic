package com.coins.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admins")
public class Admins implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "section_id")
	private Integer section_id;
	@Column(name = "name")
	private String name;
	@Column(name = "realname")
	private String realname;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "crypt")
	private String crypt;
	@Column(name = "phone")
	private String phone;
	@Column(name = "lasttime")
	private Date lasttime;
	@Column(name = "lastip")
	private String lastip;
	@Column(name = "status")
	private Integer status;
	@Column(name = "created_at")
	private Date created_at;
	@Column(name = "updated_at")
	private Date updated_at;

	private ArrayList<Role> staffposition;
	private Sections department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSection_id() {
		return section_id;
	}

	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCrypt() {
		return crypt;
	}

	public void setCrypt(String crypt) {
		this.crypt = crypt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public String getLastip() {
		return lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public ArrayList<Role> getStaffposition() {
		return staffposition;
	}

	public void setStaffposition(ArrayList<Role> staffposition) {
		this.staffposition = staffposition;
	}

	public Sections getDepartment() {
		return department;
	}

	public void setDepartment(Sections department) {
		this.department = department;
	}

}

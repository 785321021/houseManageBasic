package com.coins.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "menus")
public class Menus implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "parentid")
	private Integer parentid;
	@Column(name = "arrparentid")
	private String arrparentid;
	@Column(name = "child")
	private Integer child;
	@Column(name = "arrchildid")
	private String arrchildid;
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	@Column(name = "label")
	private String label;
	@Column(name = "icon")
	private String icon;
	@Column(name = "display")
	private Integer display;
	@Column(name = "sort")
	private Integer sort;
	@Column(name = "created_at")
	private Date created_at;
	@Column(name = "updated_at")
	private Date updated_at;
	/**
	 * 上级节点
	 */
	private Menus parent;
	/**
	 * 子节点
	 * 
	 * @return
	 */
	private List<Menus> childs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getArrparentid() {
		return arrparentid;
	}

	public void setArrparentid(String arrparentid) {
		this.arrparentid = arrparentid;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public String getArrchildid() {
		return arrchildid;
	}

	public void setArrchildid(String arrchildid) {
		this.arrchildid = arrchildid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Menus getParent() {
		return parent;
	}

	public void setParent(Menus parent) {
		this.parent = parent;
	}

	public List<Menus> getChilds() {
		return childs;
	}

	public void setChilds(List<Menus> childs) {
		this.childs = childs;
	}

}

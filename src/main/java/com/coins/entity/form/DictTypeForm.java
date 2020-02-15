package com.coins.entity.form;

import java.util.List;

import com.coins.entity.DictType;

public class DictTypeForm extends DictType {
	private Integer page;
	private Integer size;
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}

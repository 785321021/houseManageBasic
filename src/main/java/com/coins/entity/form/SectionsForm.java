package com.coins.entity.form;

import com.coins.entity.Sections;

public class SectionsForm extends Sections {
	private String stringStatus;
	private Integer page;
	private Integer size;

	public String getStringStatus() {
		return stringStatus;
	}

	public void setStringStatus(String stringStatus) {
		this.stringStatus = stringStatus;
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

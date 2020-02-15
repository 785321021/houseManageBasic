package com.coins.entity.form;

import java.util.List;

import com.coins.entity.Dict;
import com.coins.entity.DictType;

public class DictForm extends Dict {
	private Integer page;
	private Integer size;
	private List<String> dict_type_id_list;

	
	
	public List<String> getDict_type_id_list() {
		return dict_type_id_list;
	}
	public void setDict_type_id_list(List<String> dict_type_id_list) {
		this.dict_type_id_list = dict_type_id_list;
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

package com.coins.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_dict")
public class Dict implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name = "label")//字典数据名称
	private String label;
	@Column(name = "DICT_TYPE_ID")//字典类型，与sys_dict_type关联
	private String dict_type_id;
	@Column(name = "value")//字典数据值
	private String value;
	@Column(name = "status")//状态
	private String status;
	@Column(name = "sort")//排序
	private String sort;
	
	@Column(name = "CREATE_TIME")//创建时间
	private Date create_time;
	@Column(name = "UPDATE_TIME")//修改时间
	private Date update_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDict_type_id() {
		return dict_type_id;
	}
	public void setDict_type_id(String dict_type_id) {
		this.dict_type_id = dict_type_id;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	
	
	
	
	
	

	

}

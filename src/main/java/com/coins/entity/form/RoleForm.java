package com.coins.entity.form;

import com.coins.entity.Role;

public class RoleForm extends Role{
	private Integer permissionId;
	private String empName;
	private String cStringtime;
	private String mStringtime;
    private Integer page;
    private Integer size;
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
	public String getcStringtime() {
		return cStringtime;
	}
	public void setcStringtime(String cStringtime) {
		this.cStringtime = cStringtime;
	}
	public String getmStringtime() {
		return mStringtime;
	}
	public void setmStringtime(String mStringtime) {
		this.mStringtime = mStringtime;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
}

package com.coins.common.enums;

/**
 * 数据权限
 *
 */
public enum UserDataLevel implements EnumInterface {

	/**
	 * 个人权限
	 */
	Personal(1, "个人"),

	/**
	 * 部门权限
	 */
	Department(4, "部门"),

	/**
	 * 全部权限
	 */
	All(99999, "所有");

	private String description = "";
	private int value = 0;

	private UserDataLevel(int _value, String _desc) {
		this.description = _desc;
		this.value = _value;
	}

	// 普通方法
	public static String getDescription(int _value) {
		for (UserDataLevel s : UserDataLevel.values()) {
			if (s.value == _value) {
				return s.description;
			}
		}
		return null;
	}

	/**
	 * 获取枚举
	 *
	 * @param value
	 * @return
	 */
	public static UserDataLevel fromValue(int value) {
		for (UserDataLevel s : UserDataLevel.values()) {
			if (s.value == value) {
				return s;
			}
		}
		return null;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

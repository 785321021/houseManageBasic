package com.coins.common.enums;

/**
 * 用户状态
 *
 */
public enum UserStatus  implements EnumInterface{

    InService(0, "在职"),
	Dimission(1, "离职");

	private String description = "";
	private int value = 0;

	private UserStatus(int _value, String _desc) {
		this.description = _desc;
		this.value = _value;
	}

	// 普通方法
	public static String getDescription(int _value) {
		for (UserStatus s : UserStatus.values()) {
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
	public static UserStatus fromValue(int value) {
		for (UserStatus s : UserStatus.values()) {
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

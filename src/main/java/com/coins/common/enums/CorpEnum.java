package com.coins.common.enums;

/**
 * 用户状态
 *
 */
public enum CorpEnum{

	Headquarters("0000", "总部");

	private String description = "";
	private String value = "";

	private CorpEnum(String _value, String _desc) {
		this.description = _desc;
		this.value = _value;
	}

	// 普通方法
	public static String getDescription(String _value) {
		for (CorpEnum s : CorpEnum.values()) {
			if (s.value.equals(_value)) {
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
	public static CorpEnum fromValue(String value) {
		for (CorpEnum s : CorpEnum.values()) {
			if (s.value.equals(value)) {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

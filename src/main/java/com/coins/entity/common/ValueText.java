package com.coins.entity.common;

import java.io.Serializable;

public class ValueText implements Serializable {
	private Integer value;
	private String text;

	public ValueText() {
		super();
	}

	public ValueText(Integer value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "ValueTextDto{" + "value=" + value + ", text='" + text + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ValueText that = (ValueText) o;

		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;
		return text != null ? text.equals(that.text) : that.text == null;

	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}
}

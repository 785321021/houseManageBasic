package com.coins.entity.resp;

import java.io.Serializable;

public class RespEntity implements Serializable {
	private int code;
	private String msg;
	private Object data;

	public RespEntity() {
		super();
	}

	public RespEntity(RespCode respCode, Object data) {
		code = respCode.getCode();
		msg = respCode.getMsg();
		this.data = data;
	}

	public RespEntity(RespCode respCode) {
		code = respCode.getCode();
		msg = respCode.getMsg();
		data = null;
	}

	public RespEntity(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

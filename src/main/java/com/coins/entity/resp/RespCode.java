package com.coins.entity.resp;

public enum RespCode {
//	常用的几个返回码，请严格按此码返回，方便前后端定位错误：
//	200 正常，
//	500 服务器问题，
//	400 输入参数问题，
//	401 token问题，
//	402 接口权限问题，
//	403 输入正确，
//	但其它相关数据有问题，拒绝继续执行
	SUCCESS(200, "执行成功.."), WARN(201, "请求异常，请稍后重试");

	private int code;
	private String msg;

	RespCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}

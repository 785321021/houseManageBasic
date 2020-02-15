package com.coins.common.constants;

public class BaseConstants {
	public final static String DEFAULT_PWD = "123456";
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "user";
	/**
	 * 响应成功返回码
	 */
	public final static int SUCCESS_CODE = 200;
	/**
	 * 启用状态
	 */
	public final static int ENABLE_STATUS = 1;
	/**
	 * 停用状态
	 */
	public final static int DISABLE_STATUS = 1;
	/**
	 * 权限管理根节点
	 */
	public final static Integer PRIVS_ROOT = 0;
	/**
	 * 权限管理有子节点
	 */
	public final static Integer PRIVS_CHILD_EXIST = 1;
	/**
	 * 越权 token
	 */
	public final static String SMZ_TOKEN = "smz-token";
	/**
	 * 所有人都有的权限 id
	 */
	public final static Integer PRIVS_ALL_HAVE = 20;
	/**
	 * 静态错误类型 401：未登陆 402：未授权
	 */
	public static int ERR_TYPE = 0;
	/**
	 * 2.0菜谱管理逻辑删除，status 状态值
	 */
	public final static Integer LOGIC_DEL = 2;
	
	/**
	 * 短信语音appId;
	 */
	public static final int SMS_APPID = 1400215369;
	/**
	 * 短信语音appkey;
	 */
	public static final String SMS_APPKEY = "0f004b41e9db389f890b0af0629e7c8f";
	/**
	 * 短信语音templeteId
	 */
	public static final	int SMS_TEMPLATEID = 340603;
	/**
	 * 微信 accessToken
	 */
	public static final String ACCESS_TOKEN = "access_token";
	/**
	 * 微信 sdkTiket
	 */
	public static final String SDK_TICKET = "sdk_ticket";
}

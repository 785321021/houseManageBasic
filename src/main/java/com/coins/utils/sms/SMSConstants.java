package com.coins.utils.sms;
/**
 * @class:SMSConstants
 * @descript:java 使用阿里大于短信通知验证码模板常量类
 * @date:2017年3月7日 上午10:53:12
 * @author sang
 */
public class SMSConstants {
	
	//新版本：阿里云云通信调用短信接口需要的参数：
	//产品名称:短信API产品名称（短信产品名固定，无需修改）
    public static final String PRODUCT = "Dysmsapi";
    //产品域名:短信API产品域名（接口地址固定，无需修改）
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";
	
	//短信通知模板类型：1验证码类2通知类
	//千匠用户注册发送短信验证码
	public static final String SMS_MODEL_TYPE1="1";
	//千匠用户下发订单发送订单通知
	public static final String SMS_MODEL_TYPE2="2";
	//千匠用户商品价格变动通知1
	public static final String SMS_MODEL_TYPE21="21";
	//千匠用户商品价格变动通知2
	public static final String SMS_MODEL_TYPE211="211";
	//千匠用户商品到货通知1
	public static final String SMS_MODEL_TYPE212="212";
	//千匠用户商品到货通知2
	public static final String SMS_MODEL_TYPE213="213";
	
}

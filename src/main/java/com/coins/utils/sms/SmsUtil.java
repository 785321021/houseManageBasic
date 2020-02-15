package com.coins.utils.sms;
 
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Maps;
 
/**
 * @class:SmsUtil
 * @descript:该案例是阿里云发送短信通知：
 * 注意从2017年6月30后阿里大于升级成功阿里云-云通信故短信通知接口变更
 * 旧版本：阿里大于
 * 工程依赖jar包：alipay-sdk-java-20170607114101.jar
 * 参考API：https://api.alidayu.com/docs/api.htm?apiId=25450
 * 新版本：阿里云云通信
 * 工程依赖jar包：aliyun-java-sdk-core.jar，aliyun-java-sdk-dysmsapi.jar
 * 参考API：https://help.aliyun.com/document_detail/55284.html?spm=5176.doc59210.6.555.comFKg
 * Demo工程编码采用UTF-8
 * @date:2017年3月7日 下午2:47:38
 * @author sang
 */
public class SmsUtil {
    
    /**
     * 新版本：阿里云云通信发送短信通知
     * @param smsAppKey  TOP分配给应用的AppKey,即创建的签名
     * @param smsSerect  短信签名AppKey对应的secret值
     * @param smsSign    短信签名，传入的短信签名必须是在阿里大于“管理中心-短信签名管理”中的可用签名
     * @param smsModelId 短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
     * @param smsModelType oss模板类型1表示验证码类2表示通知类型
     * @param modelParam   模板内容里面的变量
     * @param phone   用户手机号码
     * @return boolean true成功false失败 
     */
    public static boolean sendSms(String smsAppKey,String smsSerect,String smsSign,String smsModelId,JSONObject modelParam,String smsModelType,String phone){
    	//验证发送失败
    	boolean result=false;
    	try {
    		//设置超时时间,可自行调整
    		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    		//初始化acsClient,暂不支持region化
    		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAppKey, smsSerect);
    		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMSConstants.PRODUCT, SMSConstants.DOMAIN);
    		IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //待发送手机号:支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phone);
            //短信签名
            request.setSignName(smsSign);
            //短信模板ID
            request.setTemplateCode(smsModelId);
            //短信通知参数json格式
//            SmsParam smsParamVo = new SmsParam();
//            //设置短信通知模板里面的变量值
//        	if("1".equals(smsModelType)){
//            	//短信验证码中的变量${number}
//            	smsParamVo.setNumber(modelParam.getString("code"));
//            }else if("2".equals(smsModelType)){
//            	//短信通知中订单号变量${ordernumber}
//            	smsParamVo.setOrdernumber(modelParam.getString("code"));
//            }else if("21".equals(smsModelType)){
//            	smsParamVo.setDisname(modelParam.getString("disname"));
//            	smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
//            	smsParamVo.setOfollowprice(modelParam.getString("ofollowprice"));
//            	smsParamVo.setNfollowprice(modelParam.getString("nfollowprice"));
//            }else if("211".equals(smsModelType)){
//            	smsParamVo.setBsetname(modelParam.getString("bsetname"));
//            	smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
//            }else if("212".equals(smsModelType)){
//            	smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
//            }else if("213".equals(smsModelType)){
//            	
//            }
            HashMap<String,String> map = Maps.newHashMap();
            map.put("hello","短信");
            
        	String smsParam = JSONObject.toJSONString(map);
            System.out.println("新版本短信通知参数smsParam:"+smsParam);
            //模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(smsParam);
            //调用阿里云云通信短信接口
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("短信接口返回 Code=" + sendSmsResponse.getCode());
            System.out.println("短信接口返回 Message=" + sendSmsResponse.getMessage());
            //接口返回结果
            if(sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            	//设置返回结果
        		result=true;
            }
		} catch (ClientException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    
    /**
     * 随机生成6位数字作为验证码
     * @return
     */
    public static String getCode(){
    	int code=(int)(Math.random()*9000+100000);
    	return code + "";
    }
    
    //测试
    public static void main(String[] args) {
 
    	//新版本测试
    	String smsAppKey="LTAIHmc******AzC";
    	String smsSerect="lVeVM3qsj9FmO3hvy**********lG5";
    	String smsSign="国商健翔";
    	String smsModelId="SMS_105475***";
    	JSONObject modelParam=new JSONObject();
    	modelParam.put("code", getCode());
    	String smsModelType="1";
    	String phone="183******12";
        sendSms(smsAppKey,smsSerect,smsSign,smsModelId,modelParam,smsModelType,phone);
    	 
	}
    
    
}

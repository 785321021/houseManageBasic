package com.coins.utils.sms;

import java.io.IOException;

import org.json.JSONException;

import com.coins.common.constants.BaseConstants;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.TtsVoiceSender;
import com.github.qcloudsms.TtsVoiceSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

/**
 * 验证码短信
 * 
 * @author duyl
 * @date 2019年6月17日下午2:47:05
 */
public class CentOSValidateSms {

	public static String senSMS(String phoneNumbers) {
		// 需要发送短信的手机号码
		try {
		    SmsSingleSender ssender = new SmsSingleSender(BaseConstants.SMS_APPID, BaseConstants.SMS_APPKEY);
		    Long curr = System.currentTimeMillis();
		    String string = curr.toString();
		    string = string.substring(string.length()-4,string.length());
		    SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers,
		    		354040, new String[] {string,"5"}, "局气餐饮", "", ""); 
		    System.out.println(result);
		   return string;
		} catch (HTTPException e) {
		    // HTTP 响应码错误
		    e.printStackTrace();
		} catch (JSONException e) {
		    // JSON 解析错误
		    e.printStackTrace();
		} catch (IOException e) {
		    // 网络 IO 错误
		    e.printStackTrace();
		}
		return "";
	}
}

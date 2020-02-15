package com.coins.utils.sms;

import java.io.IOException;

import org.json.JSONException;

import com.coins.common.constants.BaseConstants;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.SmsVoicePromptSender;
import com.github.qcloudsms.SmsVoicePromptSenderResult;
import com.github.qcloudsms.TtsVoiceSender;
import com.github.qcloudsms.TtsVoiceSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

/**
 * 语音短信
 * @author duyl
 * @date 2019年6月17日下午2:46:15
 */
public class CentOSSms {
	public static void senSMS(String vipLevel,String vipName,String tableNum,String phoneNumbers) {
		try {
			// 需要发送短信的手机号码
		    TtsVoiceSender tvsender = new TtsVoiceSender(BaseConstants.SMS_APPID, BaseConstants.SMS_APPKEY);
		    TtsVoiceSenderResult result = tvsender.send("86", phoneNumbers, BaseConstants.SMS_TEMPLATEID,
					new String[] {vipLevel,vipName,tableNum},3, "");
			System.out.println("语音"+result);
			
			SmsSingleSender ssender = new SmsSingleSender(BaseConstants.SMS_APPID, BaseConstants.SMS_APPKEY);
		    SmsSingleSenderResult result2 = ssender.sendWithParam("86", phoneNumbers,
		    		356183, new String[] {vipLevel,vipName,tableNum}, "局气餐饮", "", ""); 
		    System.out.println("短信"+result2);
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
	}
}

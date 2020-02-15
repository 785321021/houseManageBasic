//package com.coins.configure.quartz;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.coins.common.constants.BaseConstants;
//import com.coins.service.impl.RedisService;
//import com.coins.utils.WXUtils;
//
//@Component
//public class CronForWx {
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private RedisService redisService;
//	@Value("${jq.appId:}")
//	private String appId;
//	@Value("${jq.appSecret:}")
//	private String appSecret;
//
//	/**
//	 * 定时获取accessToken
//	 */
//	@Scheduled(initialDelay = 1000, fixedDelay = 7000 * 1000)
//	public void saveAccessToken() {
//		logger.info("Start saveAccessToken job .....");
//		try {
//			redisService.set(BaseConstants.ACCESS_TOKEN, WXUtils.getAccessTokenByIdAndSecret(appId, appSecret), 7200);
//		} catch (Exception e) {
//			logger.error("获取accessToken失败：", e);
//		}
//		logger.info("Complete saveAccessToken job .....");
//	}
//
//	/**
//	 * 定时获取sdkTicket
//	 */
//	@Scheduled(initialDelay = 1000, fixedDelay = 7000 * 1000)
//	public void saveSdkTicket() {
//		logger.info("Start saveSdkTicket job .....");
//		try {
//			String accessToken = "";
//			Object object = redisService.get(BaseConstants.ACCESS_TOKEN);
//			if (null == object) {
//				accessToken = WXUtils.getAccessTokenByIdAndSecret(appId, appSecret);
//			} else {
//				accessToken = (String) object;
//			}
//			redisService.set(BaseConstants.SDK_TICKET, WXUtils.getSdkTicketByAccessToken(accessToken), 7200);
//		} catch (Exception e) {
//			logger.error("获取SdkTicket失败：", e);
//		}
//		logger.info("Complete saveSdkTicket job .....");
//	}
//}

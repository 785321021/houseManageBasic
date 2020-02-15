//package com.coins.configure.quartz;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.coins.common.enums.PushTimeEnum;
//import com.coins.common.enums.SyncTypeEnum;
//import com.coins.entity.CorpInfo;
//import com.coins.entity.common.ModifyModel;
//import com.coins.entity.form.SyncParamForm;
//import com.coins.service.impl.CommonServiceImpl;
//import com.coins.service.impl.CorpDishPushServiceImpl;
//import com.coins.service.impl.CorpInfoServiceImpl;
//import com.coins.service.impl.MemberRecommendServiceImpl;
//import com.coins.service.impl.OrderServiceImpl;
//import com.coins.service.impl.PidLogsServiceImpl;
//import com.coins.service.impl.RedisService;
//import com.coins.service.impl.SyncServiceImpl;
//import com.coins.utils.DateHelper;
//
////* 第一位，表示秒，取值0-59
////* 第二位，表示分，取值0-59
////* 第三位，表示小时，取值0-23
////* 第四位，日期天/日，取值1-31
////* 第五位，日期月份，取值1-12
////* 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
////          另外：1表示星期天，2表示星期一。
////* 第7为，年份，可以留空，取值1970-2099
//@Component
//public class CronJobs {
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private MemberRecommendServiceImpl memberRecommendService;
//	@Autowired
//	private SyncServiceImpl syncService;
//	@Autowired
//	private PidLogsServiceImpl pidLogsService;
//	@Autowired
//	private CorpDishPushServiceImpl corpDishPushService;
//	@Autowired
//	private CorpInfoServiceImpl corpInfoService;
//	@Autowired
//	private CommonServiceImpl commonService;
//	@Autowired
//	private RedisService redisService;
//	@Autowired
//	private OrderServiceImpl orderService;
//	
//	/**
//	 * 每天將之前会员订单列表存入缓存
//	 */
//	@Scheduled(cron = "0 0 1 * * ?")
//	public void saveOrderMember() {
//		logger.info("Start saveOrderMember job .....");
//		try {
//			Object data = null;
//			HashMap<String,Integer> orderMemberNum = orderService.getOrderMemberNum();
//			redisService.set("orderMemberNum",orderMemberNum,24L*3600L);
//		} catch (Exception e) {
//			logger.error("订单会员统计失败：", e);
//		}
//		logger.info("Complete saveOrderMember job .....");
//	}
//	
//	@Scheduled(cron = "0 0 3 * * ?")
//	public void countRecommend() {
//		logger.info("Start countRecommend job .....");
//		try {
//			memberRecommendService.countRecommend();
//		} catch (Exception e) {
//			logger.error("会员推荐菜品统计失败：", e);
//		}
//		logger.info("Complete countRecommend job .....");
//	}
//
//	@Scheduled(cron = "0 0 2 * * ?")
//	public void synchOrder() {
//		logger.info("Start synchOrder job .....");
//		try {
//			SyncParamForm syncParamModel = new SyncParamForm();
//			syncParamModel.setStartTime(DateHelper.convertDateToString(DateHelper.addDate(new Date(), -1)));
//			syncParamModel.setEndTime(syncParamModel.getStartTime().concat(" 23:59:59"));
//			syncService.syncOrder(syncParamModel, new ModifyModel(1, "sys"));
//		} catch (Exception e) {
//			logger.error("订单同步失败：", e);
//		}
//		logger.info("Complete synchOrder job .....");
//	}
//
//	@Scheduled(cron = "0 0 10 * * ?")
//	public void tenDo() {
//		synchBaseInfo();
//	}
//
//	@Scheduled(cron = "0 30 15 * * ?")
//	public void threeHalfDo() {
//		synchBaseInfo();
//	}
//
//	private void synchBaseInfo() {
//		logger.info("Start synchBaseInfo job .....");
//		try {
//			SyncParamForm syncParamModel = new SyncParamForm();
//			// 员工同步
//			syncParamModel.setSyncType(SyncTypeEnum.EMPLOYEE.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			// 菜品同步
//			syncParamModel.setSyncType(SyncTypeEnum.ITEM.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			// 门店同步
//			syncParamModel.setSyncType(SyncTypeEnum.CORP.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			// 品项类别同步
//			syncParamModel.setSyncType(SyncTypeEnum.ITEMCLASS.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			// 支付方式
//			syncParamModel.setSyncType(SyncTypeEnum.PAYWAY.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			// 销售方案
//			syncParamModel.setSyncType(SyncTypeEnum.SalePlan.getValue());
//			List<CorpInfo> corpInfoDtos = corpInfoService.getAll();
//			corpInfoDtos.forEach(c -> {
//				syncParamModel.setCorpCode(c.getCode());
//				syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//			});
//			// 会员卡类型
//			syncParamModel.setSyncType(SyncTypeEnum.CARDTYPE.getValue());
//			syncService.syncByParam(syncParamModel, new ModifyModel(1, "sys"));
//
//		} catch (Exception e) {
//			logger.error("信息同步失败：", e);
//		}
//		logger.info("Complete synchBaseInfo job .....");
//	}
//
//	/**
//	 * 急推菜推送cron
//	 */
//	@Scheduled(cron = "0 0 0/1 * * ?")
//	public void corpDishPush() {
//		logger.info("Start corpDishPush job .....");
//		try {
//			// 获取时间
//			Integer hour = DateHelper.getHour(new Date());
//			PushTimeEnum[] values = PushTimeEnum.values();
//			for (PushTimeEnum timeEnum : values) {
//				if (timeEnum.getValue() == hour) {
//					corpDishPushService.notifyCorpDishPush(hour);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("急推菜推送失败：", e);
//		}
//		logger.info("Complete corpDishPush job .....");
//	}
//
//	/**
//	 * 每周清理一次pid日志，清除7天前
//	 */
//	@Scheduled(cron = "0 0 1 ? * 1")
//	public void clearnPidLog() {
//		logger.info("Start clean pidLog .....");
//		try {
//			pidLogsService.clear();
//		} catch (Exception e) {
//			logger.error("清理pid日志失败：", e);
//		}
//		logger.info("Complete clean Resource .....");
//	}
//
//	/**
//	 * 每周清理一次无用的资源日志 每隔7天凌晨5点，清理一次 清空步骤为：
//	 * <li>1.先清空deltemp目录中的所有文件。
//	 * <li>2.然后将无用的500文件，放入deltemp
//	 */
//	@Scheduled(cron = "0 0 5 0/7 * ?")
//	public void clearnResource() {
//		logger.info("Start clean ResourceForAll .....");
//		try {
////			commonService.cleanResourceForAll();
//		} catch (Exception e) {
//			logger.error("清理失败：", e);
//		}
//		logger.info("Complete clean ResourceForAll .....");
//	}
//}

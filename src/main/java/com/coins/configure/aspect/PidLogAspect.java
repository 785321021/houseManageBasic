//package com.coins.configure.aspect;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.coins.configure.filter.RequestWrapper;
//import com.coins.entity.PidLogs;
//import com.coins.service.impl.PidLogsServiceImpl;
//
///**
// * pid端 日志记录，包括所有请求和响应
// * 
// * @author dyl
// *
// */
//@Aspect
//@Component
//public class PidLogAspect {
//
//	private static final Logger log = LoggerFactory.getLogger(PidLogAspect.class);
//
//	@Autowired
//	private PidLogsServiceImpl pidLogsService;
//	private ThreadLocal<PidLogs> logThreadLocal = new ThreadLocal<>();
//
//	/**
//	 * 拦截pid下所有方法 后边的注解表示只切 requestMapping 的， 因为baseController 中 有 inibing 和
//	 * ModelAttribute注解 都会导致 切面走两次，一次是因为父类注解跑一遍，一次是正常controller 。这bug 找了1个小时。duyl
//	 * 2019-03-15 14:58:00
//	 */
//	@Pointcut("(execution(* com.coins.controller.pid.*.*(..)))&& (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
//	public void pointcut() {
//	}
//
//	@Before("pointcut()")
//	public void doBefore(JoinPoint joinPoint) {
//		try {
//			PidLogs pl = new PidLogs();
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//					.getRequestAttributes();
//			HttpServletRequest request = attributes.getRequest();
//			String reMethod = request.getMethod();
//			String uri = request.getRequestURI();
//			if ("GET".equals(reMethod)) {// get请求可以直接获取参数
//				pl.setRequestData(JSONObject.toJSONString(request.getParameterMap()));
//			} else {
//				// 这里需要用包装request 因为 原本的request 只允许读一次流，这里包装成 body 字段。
//				RequestWrapper myRequestWrapper;
//				myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
//				pl.setRequestData(myRequestWrapper.getBody());
//			}
//			// 组装日志数据
//			pl.setCreatedAt(new Date());
//			pl.setMethod(request.getMethod());
//			pl.setUrl(uri);
//			if(!uri.equals("/api/dish/menu")) {
//				pidLogsService.created(pl);
//			}
//			logThreadLocal.set(pl);
//		} catch (Exception e) {
//			log.error("pid记录日志失败..doBefore()***", e);
//		}
//	}
//
//	@AfterReturning(returning = "result", pointcut = "pointcut()")
//	public void doAfterReturning(Object result) {
//		try {
//			// 处理完请求，从线程变量中获取日志数据，并记录到db
//			PidLogs pl = logThreadLocal.get();
//			if (null != pl && null != pl.getId()&& ! "/api/dish/menu".equals(pl.getUrl())) {
//				pl.setResponseData(JSON.toJSONString(result));
//				pidLogsService.edit(pl);
//			}
//		} catch (Exception e) {
//			log.error("***操作请求日志记录失败doAfterReturning()***", e);
//		} finally {
//			// 清除threadlocal
//			logThreadLocal.remove();
//		}
//	}
//}
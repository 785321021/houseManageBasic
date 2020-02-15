package com.coins.configure.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.coins.configure.filter.RequestWrapper;
import com.coins.entity.Logs;
import com.coins.service.impl.LogsServiceImpl;
import com.coins.utils.JwtUtil;
import com.mysql.cj.util.StringUtils;

/**
 * pc端 日志记录，包括所有请求
 * 
 * @author dyl
 *
 */
@Aspect
@Component
public class PcLogAspect {

	private static final Logger log = LoggerFactory.getLogger(PcLogAspect.class);

	@Autowired
	private LogsServiceImpl logsService;
	private ThreadLocal<Logs> logThreadLocal = new ThreadLocal<>();

	// 拦截pid下所有方法
	@Pointcut("(execution(* com.coins.controller.home.*.*(..)))&& (@annotation(org.springframework.web.bind.annotation.RequestMapping))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			Logs pl = new Logs();
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			String reMethod = request.getMethod();
			String uri = request.getRequestURI();
			String token = request.getHeader("Authorization");
			if ("GET".equals(reMethod)) {// get请求可以直接获取参数
				pl.setData(JSONObject.toJSONString(request.getParameterMap()));
			} else {
				// 这里需要用包装request 因为 原本的request 只允许读一次流，这里包装成 body 字段。
				RequestWrapper myRequestWrapper;
				myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
				pl.setData(myRequestWrapper.getBody());
			}
			// 组装日志数据
			if (StringUtils.isNullOrEmpty(token)) {
				pl.setAdminId(0);
				pl.setUser("登录无用户");
			} else if ("smz-token".equals(token)) {
				pl.setAdminId(1);
				pl.setUser("admin");
			} else {
				pl.setAdminId(JwtUtil.parseJWTgetId(token));
				pl.setUser(JwtUtil.parseJWTgetName(token));
			}
			pl.setCreatedAt(new Date());
			pl.setMethod(request.getMethod());
			pl.setUrl(uri);
			logsService.save(pl);
			logThreadLocal.set(pl);
		} catch (Exception e) {
			log.error("pc记录日志失败..doBefore()***", e);
		}
	}

	// 切完不错任何处理
	@AfterReturning(returning = "result", pointcut = "pointcut()")
	public void doAfterReturning(Object result) {
		logThreadLocal.remove();
	}
}
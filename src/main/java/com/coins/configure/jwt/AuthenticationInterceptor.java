package com.coins.configure.jwt;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.coins.common.constants.BaseConstants;
import com.coins.service.impl.AdminsServiceImpl;
import com.coins.service.impl.MenusServiceImpl;
import com.coins.service.impl.RedisService;
import com.coins.utils.JwtUtil;

/**
 * @author dyl
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	AdminsServiceImpl userService;
	@Autowired
	MenusServiceImpl menuService;
	@Autowired
	private RedisService redisService;

	private final String CACHE = "jq_dc_umsg";
	private final String REDIRECT = "/c-api/errlog";

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		String servletPath = httpServletRequest.getServletPath();

		if (servletPath.length() > 1)//		这里获取的路径多一个’/c-api/‘ 截掉;
			servletPath = servletPath.substring(7, servletPath.length());
		try {
			String token = httpServletRequest.getHeader("Authorization");
			if (!(object instanceof HandlerMethod)) {// 如果不是映射到方法直接通过
				return true;
			}
			HandlerMethod handlerMethod = (HandlerMethod) object;
			Method method = handlerMethod.getMethod();
			// 检查是否有SkipToken注释，有则跳过认证
			if (method.isAnnotationPresent(SkipToken.class)) {
				SkipToken loginToken = method.getAnnotation(SkipToken.class);
				if (loginToken.required()) {
					return true;
				}
			}
			if (null == token) { // 空
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + REDIRECT);
				BaseConstants.ERR_TYPE = 401;
				return false;
			} else if (BaseConstants.SMZ_TOKEN.equals(token)) { // smz-token 直接过
			} else {// other
				HashMap<String, Object> mm = (HashMap<String, Object>) redisService.hget(CACHE,
						JwtUtil.parseJWTgetId(token) + "msg");
				// 判断token一样不
				if (null == mm || null == mm.get("token") || null == mm.get("allPrivsUrl")
						|| !token.equals(mm.get("token"))) {
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + REDIRECT);
					BaseConstants.ERR_TYPE = 401;
					return false;
				}
				// 这里需要判断用户是否具有查询权限,并记录日志
				// 超级管理员过
				List<Integer> ru = (List<Integer>) mm.get("allRolesId");
				List<String> rpUrl = (List<String>) mm.get("allPrivsUrl");
				if (!ru.contains(new Integer(1))) {
					// 如果不是超级管理员，判断必须有的，和自己得，是不是包含
					List<String> allMustHave = menuService.getAllMustHave();
					if (!allMustHave.contains(servletPath) && !rpUrl.contains(servletPath)) {
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + REDIRECT);
						BaseConstants.ERR_TYPE = 402;
						return false;
					}
				}
			}
		} catch (Exception j) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + REDIRECT);
			return false;
		}
		return true;
	}
}
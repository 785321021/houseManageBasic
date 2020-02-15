package com.coins.controller.home;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.common.constants.BaseConstants;
import com.coins.configure.jwt.SkipToken;
import com.coins.entity.Admins;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.AdminsServiceImpl;
import com.coins.utils.JwtUtil;
import com.mysql.cj.util.StringUtils;

@RestController
@RequestMapping("/c-api")
public class LoginController extends BaseController {

	@Autowired
	private AdminsServiceImpl userService;

	/**
	 * 登录
	 * 
	 * @param phone    用户姓名
	 * @param password 用户密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@SkipToken
	public RespEntity getLogin(String phone, String password, HttpSession session) {
		try {
			List<Admins> users = userService.getLoginInfo(phone, password);
			if (users == null || users.size() == 0) {
				return new RespEntity(201, "用户名或密码错误...", null);
			} else {
				Admins user = users.get(0);
				user.setLasttime(new Date());
				user.setLastip(getIp());
				String token = JwtUtil.createJWT(24L*3600L*1000L, user);
				userService.saveUserMsg(user, token);
				LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
				map.put("id", user.getId());
				map.put("name", user.getName());
				map.put("token", token);
				return success( "登录成功...", map);
			}
		} catch (Exception e) {
			return success( "用户名或密码错误...", null);
		}
	}

	/**
	 * 退出
	 * 
	 * @param name     用户姓名
	 * @param password 用户密码
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@SkipToken
	public RespEntity getLoginOut(HttpSession session) {
		try {
			Admins ad = getCurrentEmployee();
			session.removeAttribute(BaseConstants.SESSION_KEY);
			userService.removeUserMsg(ad);
			return success( "注销成功...", null);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SkipToken
	public RespEntity detail(@RequestBody Admins emp, HttpSession session) {
		return getLogin(emp.getPhone(), emp.getPassword(), session);
	}

	/**
	 * type 1: 未登陆 2：无权限 3：未知错误
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/errlog")
	@SkipToken
	public RespEntity loginError(HttpSession session) {
			int t = BaseConstants.ERR_TYPE;
			if (t == 401) {
				return new RespEntity(401, "请重新登录，获取验证信息", null);
			} else if (t == 402) {
				return new RespEntity(402, "无权调用此接口", null);
			}
		return new RespEntity(401, "登录/权限验证失败", null);
	}

	public String getIp() {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		if ("0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())) {
			return "127.0.0.1";
		}
		return request.getRemoteAddr();
	}
}

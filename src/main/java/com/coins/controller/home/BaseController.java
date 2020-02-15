package com.coins.controller.home;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.coins.common.constants.BaseConstants;
import com.coins.configure.aspect.PcLogAspect;
import com.coins.entity.Admins;
import com.coins.entity.common.ModifyModel;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.utils.JwtUtil;

public abstract class BaseController {
	private static final Logger log = LoggerFactory.getLogger(PcLogAspect.class);

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 获取登录用户信息 从token 中读取的 id name
	 * 
	 * @return
	 */
	public Admins getCurrentEmployee() {
		String token = request.getHeader("Authorization");
		Admins admins = new Admins();
		if (BaseConstants.SMZ_TOKEN.equals(token)) {
			admins.setId(1);
			admins.setName("admin");
		} else {
			try {
				admins.setId(JwtUtil.parseJWTgetId(token));
				admins.setName(JwtUtil.parseJWTgetName(token));
			} catch (Exception e) {
				log.error("解析token出错，需要重新登录", e);
			}
		}
		return admins;
	}

	/**
	 * 不带参数的成功响应
	 */
	public final static RespEntity SUCCESS = new RespEntity(RespCode.SUCCESS);
	/**
	 * 不带参数的失败响应
	 */
	public final static RespEntity WARN = new RespEntity(RespCode.WARN);
	/**
	 * 带1个参数的响应
	 */
	public RespEntity success(Object data) {
		return new RespEntity(RespCode.SUCCESS,data);
	}
	/**
	 * 带2个参数的响应
	 */
	public RespEntity success(String msg ,Object data) {
		return new RespEntity(BaseConstants.SUCCESS_CODE,msg,data);
	}
	/**
	 * 带1个参数的失败响应//默认201
	 */
	public RespEntity warn(Object data) {
		return new RespEntity(RespCode.WARN,data);
	}
	/**
	 * 带2个参数的失败响应//默认201
	 */
	public RespEntity warn(String msg,Object data) {
		return new RespEntity(201,msg,data);
	}
	
	
	/**
	 * 获取session
	 * 
	 * @return
	 */
	public HttpSession getLoginInfo() {
		return request.getSession();
	}

	protected final ModifyModel getModifyModel() {
		ModifyModel modifyModel = new ModifyModel();
		modifyModel.setEmployeeId(getCurrentEmployee().getId());
		modifyModel.setEmployeeName(getCurrentEmployee().getName());
		return modifyModel;
	}
	
	/**
	 * 导出Excel时 发送响应流方法
	 * @param response
	 * @param fileName
	 */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

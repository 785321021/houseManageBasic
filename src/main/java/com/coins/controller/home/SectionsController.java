package com.coins.controller.home;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.common.constants.BaseConstants;
import com.coins.entity.Sections;
import com.coins.entity.common.PageData;
import com.coins.entity.form.SectionsForm;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.SectionsServiceImpl;

@RestController
@RequestMapping("/c-api/section")
public class SectionsController extends BaseController {
	@Autowired
	private SectionsServiceImpl sectionsService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RespEntity list(@RequestBody SectionsForm form, HttpServletRequest request) {
		try {
			if(null == form.getPage()) {//如果不是分页查询返回所有部门做下拉框  //需要写一个size 参数，因为如果无参数，无法进post请求
				return success(sectionsService.getAll());
			}
			PageData pd = sectionsService.selectPage(form);
			List<Sections> arr = pd.getArr();
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("total", pd.getTotal());
			data.put("list", pd.getArr());
			return success( "查询部门列表成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/detail",method = RequestMethod.POST)
	public RespEntity detail(@RequestBody Sections section, HttpSession session) {
		try {
			Sections sec = sectionsService.getInfo(section.getId());
			return success( "查询成功...", sec);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = {"/create","/status"}, method = RequestMethod.POST)
	public RespEntity create(@RequestBody Sections section) {
		try {
			sectionsService.addOrUpdate(section);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public RespEntity remove(@RequestBody Sections section) {
		try {
			sectionsService.delete(section.getId());
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}
}

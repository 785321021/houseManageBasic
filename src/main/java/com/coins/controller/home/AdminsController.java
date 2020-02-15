package com.coins.controller.home;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.common.constants.BaseConstants;
import com.coins.entity.Admins;
import com.coins.entity.common.PageData;
import com.coins.entity.form.AdminsForm;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.AdminsServiceImpl;

@RestController
@RequestMapping("/c-api/admin")
public class AdminsController extends BaseController {
	@Autowired
	private AdminsServiceImpl adminsService;

	/**
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RespEntity list(@RequestBody AdminsForm form) {
		PageData pd = new PageData();
		System.out.println("11111111111111111");
		try {
			if(null == form.getPage()) {
				return success("查人人员列表成功",adminsService.getAll());
			}
			pd = adminsService.getList(form);
			List<AdminsForm> arr = pd.getArr();
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("total", pd.getTotal());
			data.put("list", arr);
			return success("查询列表成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = { "/create", "/editinfo" }, method = RequestMethod.POST)
	public RespEntity create(@RequestBody AdminsForm form) {
		try {
			adminsService.addOrUpdate(form);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping("/detail")
	public RespEntity detail(@RequestBody AdminsForm form) {
		try {
			Admins sec = adminsService.getInfo(form.getId());
			return success( "查询详情成功...", sec);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = { "/selfeditinfo", "/status" }, method = RequestMethod.POST)
	public RespEntity selfeditinfo(@RequestBody AdminsForm form) {
		try {
			adminsService.selfEditInfo(form);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

	/**
	 * 删除
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public RespEntity remove(@RequestBody AdminsForm form) {
		try {
			adminsService.delete(form.getId());
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = { "/editpassword", "/selfeditpassword" }, method = RequestMethod.POST)
	public RespEntity editPassword(@RequestBody AdminsForm admins) {
		try {
			if (null == admins.getId()) {
				admins.setId(this.getCurrentEmployee().getId());
			}
			adminsService.editPassword(admins);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}
}

package com.coins.controller.home;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coins.common.constants.BaseConstants;
import com.coins.entity.common.PageData;
import com.coins.entity.form.LogsForm;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.LogsServiceImpl;

@Controller
@RequestMapping("/c-api/log")
public class LogsController extends BaseController {

	@Autowired
	private LogsServiceImpl logsService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity getAllByPage(@RequestBody LogsForm form) {
		try {
			if(null != form.getAdminId() && form.getAdminId().intValue()== 0)
				form.setAdminId(null);
			PageData pd = logsService.selectPage(form);
			int total = pd.getTotal();
			List<LogsForm> pageList = (List<LogsForm>) pd.getArr();
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("list", pageList);
			data.put("total", total);
			return success( "查询操作日志成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ResponseBody
	public RespEntity clear(HttpServletRequest request) {
		try {
			logsService.clear();
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

}

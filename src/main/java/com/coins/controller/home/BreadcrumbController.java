package com.coins.controller.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.Menus;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.MenusServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysql.cj.util.StringUtils;

@RestController
@RequestMapping("/c-api/breadcrumb")
public class BreadcrumbController extends BaseController {
	@Autowired
	MenusServiceImpl menusService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity getAllByPage(String label, HttpServletRequest req) {
		try {
			HashMap<String, Object> data = Maps.newHashMap();
			ArrayList<Object> father = Lists.newArrayList();
			HashMap<String, String> inData = Maps.newHashMap();
			inData.put("name", "首页");
			inData.put("to", "/console/index/index");
			father.add(inData);
			Menus last = menusService.getMenuByLable(label);
			if (null != last) {
				data.put("title", last.getName());
				data.put("btns", Lists.newArrayList());

				String parIds[] = last.getArrparentid().split(",");
				for (String id : parIds) {
					int mid = Integer.parseInt(id);
					HashMap<String, String> inData2 = Maps.newHashMap();
					Menus info = menusService.getInfo(mid);
					if (null != info ) {
						
						inData2.put("name", info.getName());
						if (info.getParentid().intValue() == 0) {
							inData2.put("to", "");
						} else {
							inData2.put("to", "/console/"+info.getUrl());
						}
						father.add(inData2);
					}
				}
				HashMap<String, String> inData3 = Maps.newHashMap();
				inData3.put("name", last.getName());
				inData3.put("to", "/console/"+last.getUrl());
				father.add(inData3);				
			}
			data.put("breadcrumb", father);
			return success( data);
		} catch (Exception e) {
			return warn(e);
		}
	}
}

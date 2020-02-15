package com.coins.controller.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.common.constants.BaseConstants;
import com.coins.common.enums.YesNoEnum;
import com.coins.entity.Menus;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.MenusServiceImpl;
import com.coins.utils.DateUtils;

@RestController
@RequestMapping("/c-api/menu")
public class MenusController extends BaseController {
	@Autowired
	private MenusServiceImpl menusService;

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public RespEntity tree() {
		try {
			List<Menus> menus = menusService.getTree(1);
			List<LinkedHashMap<String, Object>> data = getTree(menus);
			return success( "获取权限数据成功...", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	/**
	 * 权限详情
	 * 
	 * @param
	 * @param session
	 * @return
	 */
	@RequestMapping("/detail")
	public RespEntity detail(@RequestBody Menus menu, HttpSession session) {
		try {
			Menus sec = menusService.getInfo(menu.getId());
			LinkedHashMap<String, Object> data = getDetailData(sec);
			return success( "查询成功...", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	/**
	 * 权限展示 /permission/list
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public RespEntity list() {
		try {
			List<Menus> menus = menusService.getList(this.getCurrentEmployee().getId());
			return success( "获取权限数据成功...", getListF(menus));
		} catch (Exception e) {
			return warn(e);
		}
	}

	/**
	 * 下拉菜单 /permission/select
	 * 
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public RespEntity select() {
		try {
			List<Menus> menus = menusService.getAll();
			ArrayList<HashMap<String, Object>> arr = new ArrayList<HashMap<String, Object>>();
			for (Menus menu : menus) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("label", menu.getName());
				map.put("value", menu.getId());
				arr.add(map);
			}
			return success( "获取权限数据成功...", arr);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RespEntity create(@RequestBody Menus menu) {
		try {
			menu.setId(null);
			menu = menusService.add(menu);
			return success( "创建权限成功...", getDetailData(menu));
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public RespEntity edit(@RequestBody Menus menu) {
		try {
			menu = menusService.update(menu);
			return success( "更新权限成功...", getDetailData(menu));
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public RespEntity remove(@RequestBody Menus menu) {
		try {
			menusService.delete(menu.getId());
			return success( "删除权限成功...", null);
		} catch (Exception e) {
			return warn(e);
		}
	}

	/**
	 * 权限树
	 * 
	 * @param menus
	 * @return
	 */
	private List<LinkedHashMap<String, Object>> getTree(List<Menus> menus) {
		List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
		for (Menus mee : menus) {
			LinkedHashMap<String, Object> arg = new LinkedHashMap<String, Object>();
			arg.put("menu_id", mee.getId());
			arg.put("title", mee.getName());
			arg.put("expand", false);
			arg.put("sort", mee.getSort());
			if (BaseConstants.PRIVS_CHILD_EXIST.equals(mee.getChild()) && null != mee.getChilds()
					&& mee.getChilds().size() > 0) {
				arg.put("children", getTree(mee.getChilds()));
			} else {
				arg.put("children", new ArrayList<LinkedHashMap<String, Object>>());
			}
			data.add(arg);
		}
		return data.stream().sorted((a, b) -> (Integer) a.get("sort") - (Integer) b.get("sort"))
				.collect(Collectors.toList());
	}

	/**
	 * 权限列表/这里只组装2层，这里是第一层
	 * 
	 * @param menus 可用权限
	 * @param deep  深度
	 * @return
	 */
	private List<LinkedHashMap<String, Object>> getListF(List<Menus> menus) {
		List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
		for (Menus mee : menus) {
			LinkedHashMap<String, Object> arg = new LinkedHashMap<String, Object>();
			arg.put("id", mee.getId());
			arg.put("parentid", mee.getParentid());
			arg.put("name", mee.getName());
			arg.put("url", mee.getUrl());
			arg.put("label", mee.getLabel());
			arg.put("icon", mee.getIcon());
			arg.put("sort", mee.getSort());
			if (BaseConstants.PRIVS_CHILD_EXIST.equals(mee.getChild()) && null != mee.getChilds()
					&& mee.getChilds().size() > 0) {
				// 这里只组装2层
				arg.put("submenu", getListS(mee.getChilds()));
			} else {
				arg.put("submenu", new ArrayList<LinkedHashMap<String, Object>>());
			}
			data.add(arg);
		}
		return data.stream().sorted((a, b) -> (Integer) a.get("sort") - (Integer) b.get("sort"))
				.collect(Collectors.toList());
	}

	/**
	 * 权限列表/这里只组装2层，这里是第2层
	 * 
	 * @param menus 可用权限
	 * @param deep  深度
	 * @return
	 */
	private List<LinkedHashMap<String, Object>> getListS(List<Menus> menus) {
		List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
		for (Menus mee : menus) {
			LinkedHashMap<String, Object> arg = new LinkedHashMap<String, Object>();
			arg.put("id", mee.getId());
			arg.put("parentid", mee.getParentid());
			arg.put("name", mee.getName());
			arg.put("url", mee.getUrl());
			arg.put("label", mee.getLabel());
			arg.put("icon", mee.getIcon());
			arg.put("sort", mee.getSort());
			arg.put("submenu", new ArrayList<LinkedHashMap<String, Object>>());
			data.add(arg);
		}
		return data.stream().sorted((a, b) -> (Integer) a.get("sort") - (Integer) b.get("sort"))
				.collect(Collectors.toList());
	}

	/**
	 * 详情
	 * 
	 * @param sec
	 * @return
	 */
	private LinkedHashMap<String, Object> getDetailData(Menus sec) {
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("id", sec.getId());
		data.put("parentid", sec.getParentid());
//		data.put("arrparentid", sec.getArrparentid());
//		data.put("child", sec.getChild());
//		data.put("arrchildid", sec.getArrchildid());
		data.put("name", sec.getName());
		data.put("url", sec.getUrl());
		data.put("label", sec.getLabel());
		data.put("icon", sec.getIcon());
		data.put("display", (sec.getDisplay().intValue()==YesNoEnum.Yes.getValue())?true:false);
		data.put("sort", sec.getSort());
//		if (null != sec.getCreated_at()) {
//			data.put("created_at", DateUtils.formatTimestamp.format(sec.getCreated_at()));
//		} else {
//			data.put("created_at", "");
//		}
//		if (null != sec.getUpdated_at()) {
//			data.put("updated_at", DateUtils.formatTimestamp.format(sec.getUpdated_at()));
//		} else {
//			data.put("updated_at", "");
//		}
		return data;
	}
}

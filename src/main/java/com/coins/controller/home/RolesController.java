package com.coins.controller.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.common.constants.BaseConstants;
import com.coins.entity.Menus;
import com.coins.entity.RolePrivs;
import com.coins.entity.Roles;
import com.coins.entity.common.PageData;
import com.coins.entity.form.RolesForm;
import com.coins.entity.resp.RespCode;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.MenusServiceImpl;
import com.coins.service.impl.RolesServiceImpl;

@RestController
@RequestMapping("/c-api/role")
public class RolesController extends BaseController {
	@Autowired
	private RolesServiceImpl roleService;
	@Autowired
	private MenusServiceImpl menusService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RespEntity list(@RequestBody RolesForm form) {
		try {
			if (null == form.getPage()) {// 如果不是分页查询返回所有部门做下拉框 //需要写一个size 参数，因为如果无参数，无法进post请求
				return success( roleService.getAllRoles());
			}
			PageData pd = roleService.selectPage(form);
			int total = pd.getTotal();
			List<Roles> arrList = (List<Roles>) pd.getArr();
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("total", total);
			data.put("list", arrList);
			return success( "查询角色列表成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = { "/create", "/edit", "/status" }, method = RequestMethod.POST)
	public RespEntity create(@RequestBody Roles role) {
		try {
			roleService.addOrUpdate(role);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public RespEntity remove(@RequestBody Roles role) {
		try {
			roleService.delete(role.getId());
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/priv", method = RequestMethod.GET)
	public RespEntity permission(Integer role_id) {
		try {
			List<Menus> allList = menusService.getAllMenus();// 获取所有权限
			List<RolePrivs> privsList = roleService.getPrivsList(role_id);
			List<LinkedHashMap<String, Object>> data = this.getTree(allList, privsList);
			return success( "获取权限列表成功...", data);
		} catch (Exception e) {
			return warn(e);
		}
	}

	@RequestMapping(value = "/priv", method = RequestMethod.POST)
	public RespEntity update(@RequestBody RolesForm form) {
		try {
			roleService.updateRolePrivs(form.getId(), form.getMenu_id());
			return success( "更新权限菜单成功...", null);
		} catch (Exception e) {
			return warn(e);
		}
	}

	// ==========================暂时放在这里
	/**
	 * 查询权限列表
	 * 
	 * @param privsList
	 */
	private List<LinkedHashMap<String, Object>> getTree(List<Menus> mList, List<RolePrivs> privsList) {
		List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();
		boolean flag = false;
		for (Menus menus : mList) {
			// 一级节点
			if (BaseConstants.PRIVS_ROOT.equals(menus.getParentid())) {
				flag = false;// 默认勾选状态为不勾选，如果 privsList中存在，则勾选
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("menu_id", menus.getId());
				map.put("title", menus.getName());
				map.put("expand", true);
				List<Integer> collect = privsList.stream().map(pl -> pl.getMenu_id()).collect(Collectors.toList());
				if (collect.contains(menus.getId()))
					flag = true;
				map.put("children", getChild(menus, mList, privsList));
				if (!BaseConstants.PRIVS_CHILD_EXIST.equals(menus.getChild()) || !menusService.hasDisplayChild(menus)) {// 这里还需要判断子类都是不显示的。如果都不显示，则此层可以勾选
					map.put("checked", flag);
				}
				data.add(map);
			}
		}
		return data;
	}

	/**
	 * 查询子节点
	 * 
	 * @param m
	 * @return
	 */
	private List<LinkedHashMap<String, Object>> getChild(Menus menus, List<Menus> mList, List<RolePrivs> privsList) {
		List<LinkedHashMap<String, Object>> childList = new ArrayList<LinkedHashMap<String, Object>>();
		if (BaseConstants.PRIVS_CHILD_EXIST.equals(menus.getChild())) {
			boolean flag = false;
			for (Menus am : mList) {
				if (am.getParentid().equals(menus.getId())) {
					flag = false;
					LinkedHashMap<String, Object> hm = new LinkedHashMap<String, Object>();
					hm.put("menu_id", am.getId());
					hm.put("title", am.getName());
					hm.put("expand", true);
					List<Integer> collect = privsList.stream().map(pl -> pl.getMenu_id()).collect(Collectors.toList());
					if (collect.contains(am.getId()))
						flag = true;
					if (!BaseConstants.PRIVS_CHILD_EXIST.equals(am.getChild()) || !menusService.hasDisplayChild(am)) {
						hm.put("checked", flag);
					}
					hm.put("children", getChild(am, mList, privsList));
					childList.add(hm);
				}
			}
		}
		return childList;
	}
}

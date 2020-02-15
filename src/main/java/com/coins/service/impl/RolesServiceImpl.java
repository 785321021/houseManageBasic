package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coins.common.constants.BaseConstants;
import com.coins.entity.Menus;
import com.coins.entity.RolePrivs;
import com.coins.entity.Roles;
import com.coins.entity.common.PageData;
import com.coins.entity.form.RolesForm;
import com.coins.mapper.RolePrivsMapper;
import com.coins.mapper.RolesMapper;
import com.google.common.collect.Lists;
import com.mysql.cj.util.StringUtils;

@Transactional
@Service
public class RolesServiceImpl extends BaseServiceImpl {
	@Autowired
	private RolesMapper rolesMapper;
	@Autowired
	private MenusServiceImpl menusService;
	@Autowired
	private RolePrivsMapper rolePrivsMapper;

	private final String CACHE = "jq_dc_roles";

	public PageData selectPage(RolesForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from roles where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		String name = form.getName();
		if (!StringUtils.isNullOrEmpty(name)) {
			sql += " and name like ? ";
			arg.add("%" + name + "%");
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<Roles>());
			return pd;
		}
		sql += "limit ?,?";
		arg.add(getBgn(form.getPage(), form.getSize()));
		arg.add(getEnd(form.getPage(), form.getSize()));
		List<Roles> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(Roles.class));
		pd.setArr(query);
		return pd;
	}

	@CachePut(value = CACHE, key = "#roles.id")
	public Roles addOrUpdate(Roles roles) {
		roles.setUpdated_at(new Date());
		if (null == roles.getId()) {// 新增
			roles.setCreated_at(new Date());
			rolesMapper.insertSelective(roles);
			List<Integer> list = Lists.newArrayList();
			list.add(BaseConstants.PRIVS_ALL_HAVE);
			updateRolePrivs(roles.getId(), list);
			return roles;
		} else {// 修改
			rolesMapper.updateByPrimaryKeySelective(roles);
			return roles;
		}
	}

	@Cacheable(value = CACHE, key = "#id")
	public Roles getInfo(Integer id) {
		return rolesMapper.selectByPrimaryKey(id);
	}

	@CacheEvict(value = CACHE, key = "#id")
	public int delete(Integer id) {
		return rolesMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 查询当前角色权限
	 */
	public List<RolePrivs> getPrivsList(int role_id) {
		RolePrivs rp = new RolePrivs();
		rp.setRole_id(role_id);
		return rolePrivsMapper.select(rp);
	}

	/**
	 * 删除在新增
	 */
	@CacheEvict(value = "jq_dc_menus", allEntries = true)
	public int updateRolePrivs(Integer role_id, List<Integer> list) {
		// 这里添加上必须新增的
//		list.add(BaseConstants.PRIVS_ALL_HAVE);
		RolePrivs rpp = new RolePrivs();
		rpp.setRole_id(role_id);
		rolePrivsMapper.delete(rpp);
		// 这里有些权限必须有
		Map<Integer, Menus> allMenus = menusService.getAll().stream()
				.collect(Collectors.toMap(is -> is.getId(), is -> is));
		List<RolePrivs> addList = Lists.newArrayList();
		Set<Integer> set = new HashSet<Integer>();
		for (Integer j : list) {
			Menus menus = allMenus.get(j);
			if (!j.equals(BaseConstants.PRIVS_ALL_HAVE) && menus.getParentid().intValue() == 0) {
				// 这里是父类，只加自己
				set.add(j);
			} else {// 这里不是父类，加自己及自己下级
				set.add(j);
				String[] split = menus.getArrchildid().split(",");
				for (String s : split) {
					set.add(Integer.parseInt(s));
				}
			}
		}
		for (Integer i : set) {
			Menus menus = allMenus.get(i);
//			if(menus.getArrparentid().split(",").length==2) {//这里需要没有显示的子类
			RolePrivs rp = new RolePrivs();
			rp.setMenu_id(i);
			rp.setRole_id(role_id);
			rp.setCreated_at(new Date());
			rp.setUpdated_at(new Date());
			rp.setLabel(menus.getLabel());
			rp.setUrl(menus.getUrl());
			addList.add(rp);
//			}
		}
		return rolePrivsMapper.insertList(addList);
	}

	@Cacheable(value=CACHE,key="#root.methodName")
	public List<Roles> getAllRoles() {
		return rolesMapper.selectAll();
	}
}

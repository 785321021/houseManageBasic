package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coins.common.constants.BaseConstants;
import com.coins.common.enums.YesNoEnum;
import com.coins.entity.Menus;
import com.coins.mapper.MenusMapper;
import com.google.common.collect.Lists;
import com.mysql.cj.util.StringUtils;

@Transactional
@Service
public class MenusServiceImpl extends BaseServiceImpl {
	@Autowired
	private MenusMapper menusMapper;
	private final String CACHE = "jq_dc_menus";

	/**
	 * 查询权限列表
	 */
	public List<Menus> getTree(int id) {
		List<Menus> mList = new ArrayList<Menus>();
		if (id == 0 || id == 1) {
			mList = menusMapper.selectAll();
		} else {
			mList = menusMapper.selectByUserIdTree(id);
		}
		List<Menus> first = new ArrayList<Menus>();
		for (Menus menus : mList) {
			// 一级节点
			if (BaseConstants.PRIVS_ROOT == menus.getParentid()) {
				if (true) {
					first.add(getChild(menus, mList));
				}
			}
		}
		return first;
	}

	/**
	 * 所有有效权限
	 * 
	 * @return
	 */
	@Cacheable(value = CACHE, key = "#root.methodName")
	public List<Menus> getAllMenus() {
		Menus mm = new Menus();
		mm.setDisplay(YesNoEnum.Yes.getValue());
		return menusMapper.select(mm);
	}

	/**
	 * 所有
	 * 
	 * @return
	 */
	@Cacheable(value = CACHE, key = "#root.methodName")
	public List<Menus> getAll() {
		return menusMapper.selectAll();
	}

	/**
	 * 查询权限树
	 */
	public List<Menus> getList(int id) {
		List<Menus> mList = new ArrayList<Menus>();
		if (id == 0 || id == 1) {
			mList = getAllMenus();
		} else {
			mList = menusMapper.selectByUserId(id);
		}
		List<Menus> first = new ArrayList<Menus>();
		for (Menus menus : mList) {
			// 一级节点
			if (BaseConstants.PRIVS_ROOT == menus.getParentid()) {
				if (true) {
					first.add(getChild(menus, mList));
				}
			}
		}
		return first;
	}

	/**
	 * 新建权限
	 */
	@CacheEvict(value = CACHE, allEntries = true)
	public Menus add(Menus menu) {
		// 新增操作
		menu.setCreated_at(new Date());
		menu.setUpdated_at(new Date());
		menusMapper.insertSelective(menu);
		// 更新子节点，将自己id 扔进去
		menu.setArrchildid(menu.getId() + "");
		menusMapper.updateByPrimaryKeySelective(menu);
		// 更新父节点
		updateArrP(menu.getId());
		if (menu.getParentid() == 0) {
			return menu;
		}
		// 更新所有父节点的子节点
		int yuanp = menu.getParentid();
		while (yuanp != 0) {// 原父节点更新子节点
			Menus yuan2m = menusMapper.selectByPrimaryKey(yuanp);
			updateArrch(yuanp);
			yuanp = yuan2m.getParentid();
		}
		return menu;
	}

	public Menus getInfo(int id) {
		return menusMapper.selectByPrimaryKey(id);
	}

	@CacheEvict(value = CACHE, allEntries = true)
	public int delete(Integer id) {
		// 先修改父菜单中的子菜单，如果父菜单没有其他子菜单，还要设置child
		Menus mm = menusMapper.selectByPrimaryKey(id);
		HashSet<Integer> childTwo = getChildTwo(id);
		for (Integer idd : childTwo) {
			menusMapper.deleteByPrimaryKey(idd);
		}
		if (mm.getParentid() == 0)
			return 1;
		int yuanp = mm.getParentid();
		while (yuanp != 0) {// 父节点更新子节点
			Menus yuan2m = menusMapper.selectByPrimaryKey(yuanp);
			updateArrch(yuanp);
			yuanp = yuan2m.getParentid();
		}
		return 1;
	}

	@CacheEvict(value = CACHE, allEntries = true)
	public Menus update(Menus menu) {
		// 先查询出原节点信息
		Menus yuanm = menusMapper.selectByPrimaryKey(menu.getId());
		// 更新节点
		menu.setUpdated_at(new Date());
		menusMapper.updateByPrimaryKeySelective(menu);
		// 判断是否更新节点位置
		if (null != menu.getParentid()) {
			int yuanp = yuanm.getParentid();
			int xianp = menu.getParentid();
			while (yuanp != 0) {// 原父节点更新子节点
				Menus yuan2m = menusMapper.selectByPrimaryKey(yuanp);
				updateArrch(yuanp);
				yuanp = yuan2m.getParentid();
			}
			while (xianp != 0) {// 当前父节点更新子节点
				Menus xian2m = menusMapper.selectByPrimaryKey(xianp);
				updateArrch(xianp);
				xianp = xian2m.getParentid();
			}
			// 自己及子节点更新父节点
			String[] split = getArrchild(menu.getId()).split(",");
			for (String sid : split) {
				updateArrP(Integer.parseInt(sid));
			}
		}
		return menu;
	}

	/**
	 * 更新当前id arrp
	 * 
	 * @param id
	 */
	private void updateArrP(Integer id) {
		Menus mm = new Menus();
		mm.setId(id);
		mm.setArrparentid(getArrp(id));
		menusMapper.updateByPrimaryKeySelective(mm);
	}

	/**
	 * 更新当前id arrc
	 * 
	 * @param id
	 */
	private void updateArrch(Integer id) {
		Menus mm = new Menus();
		mm.setId(id);
		mm.setArrchildid(getArrchild(id));
		if (mm.getArrchildid().split(",").length == 1) {
			mm.setChild(0);// 如果子节点只有自己则，至child为0
		} else {
			mm.setChild(1);
		}
		menusMapper.updateByPrimaryKeySelective(mm);
	}

	/**
	 * 获取所有父节点
	 */
	private String getArrp(int id) {
		Menus menus = menusMapper.selectByPrimaryKey(id);
		String arrp = "";
		if (menus.getParentid().intValue() != 0) {
			arrp = getArrp(menus.getParentid()) + "," + menus.getParentid() + ",";
		} else {
			arrp = menus.getParentid() + ",";
		}
		return arrp.substring(0, arrp.length() - 1);
	}

	/**
	 * 获取所有子节点
	 */
	private String getArrchild(int id) {
		HashSet<Integer> childTwo = getChildTwo(id);
		Integer[] arr = new Integer[childTwo.size()];
		arr = childTwo.toArray(arr);
		Arrays.sort(arr);
		String arrc = "";
		for (Integer i : arr) {
			arrc += i + ",";
		}
		return arrc.substring(0, arrc.length() - 1);
	}

	/**
	 * 查询子节点
	 * 
	 * @param m
	 * @return
	 */
	private Menus getChild(Menus m, List<Menus> mList) {
		// 如果有子节点
		if (BaseConstants.PRIVS_CHILD_EXIST == m.getChild()) {
			ArrayList<Menus> cList = new ArrayList<Menus>();
			for (Menus am : mList) {
				if (am.getParentid().intValue() == m.getId().intValue()) {
					cList.add(getChild(am, mList));
				}
			}
			m.setChilds(cList);
		}
		return m;
	}

	/**
	 * 查询子节点（间接）id
	 */
	private HashSet<Integer> getChildTwo(int id) {
		HashSet<Integer> arr = new HashSet<Integer>();
		arr.add(id);
		Menus m = new Menus();
		m.setParentid(id);
		List<Menus> select = menusMapper.select(m);
		if (null != select && select.size() > 0) {
			for (Menus mm : select) {
				arr.add(mm.getId());
				arr.addAll(getChildTwo(mm.getId()));
			}
		}
		return arr;
	}

	public Menus getMenuByLable(String label) {
		if (StringUtils.isNullOrEmpty(label))
			return null;
		Menus menu = new Menus();
		menu.setLabel(label);
		return menusMapper.selectOne(menu);
	}

	/**
	 * 有没有 不显示的 子节点
	 */
	public boolean hasDisplayChild(Menus menu) {
		Menus mm = new Menus();
		mm.setParentid(menu.getId());
		List<Menus> select = menusMapper.select(mm);
		for (Menus m : select) {
			if (m.getDisplay().intValue() == YesNoEnum.Yes.getValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取所有必有menu 的label
	 * @return
	 */
	@Cacheable(value = CACHE, key = "#root.methodName")
	public List<String> getAllMustHave(){
		Menus menu = new Menus();
		menu.setId(BaseConstants.PRIVS_ALL_HAVE);
		menu = menusMapper.selectOne(menu);
		String[] split = menu.getArrchildid().split(",");
		List<Integer> mid = Lists.newArrayList();
		for(String s:split) {
			mid.add(Integer.parseInt(s));
		}
		return getAll().stream().filter(c -> mid.contains(c.getId())).map(d ->d.getLabel()).collect(Collectors.toList());
	}
}

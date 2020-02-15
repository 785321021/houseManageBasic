//package com.coins.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import com.coins.common.enums.YesNoEnum;
//import com.coins.entity.Department;
//import com.coins.entity.EmployeeRole;
//import com.coins.entity.Role;
//import com.coins.entity.common.ModifyModel;
//import com.coins.entity.common.PageData;
//import com.coins.entity.form.RoleForm;
//import com.coins.mapper.RoleMapper;
//import com.coins.utils.ModelHelper;
//
//@Transactional
//@Service
//public class RoleServiceImplForOldJuqi extends BaseServiceImpl {
//	@Autowired
//	private RoleMapper roleMapper;
//	@Autowired
//	private EmployeeRoleServiceImpl employeeRoleService;
//
//	private final String ROLE_CACHE_KEY = "ALL_ROLE_CACHE";
//	/**
//	 * 角色列表
//	 * @param form
//	 * @return
//	 */
//	public PageData selectPage(RoleForm form) {
//		PageData pd = new PageData();
//		String sqlList = " select * ";
//		String sqlCount = "select count(1) as total ";
//		String sql = "  from role where deleted = 0 ";
//		ArrayList<Object> arg = new ArrayList<Object>();
//		String name = form.getName();
//		if (!StringUtils.isEmpty(name)) {
//			sql += " and name like ? ";
//			arg.add("%" + name + "%");
//		}
//		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
//		pd.setTotal(total);
//		if (total == 0) {
//			pd.setArr(new ArrayList<Department>());
//			return pd;
//		}
//		sql += "limit ?,?";
//		arg.add(getBgn(form.getPage(), form.getSize()));
//		arg.add(getEnd(form.getPage(), form.getSize()));
//		List<Role> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(Role.class));
//		pd.setArr(query);
//		return pd;
//	}
//	/**
//	 * 权限修改处列表
//	 * @param form
//	 * @return
//	 */
//	public PageData selectPageByPermission(RoleForm form) {
//		PageData pd = new PageData();
//		ArrayList<Object> arg = new ArrayList<Object>();
//		String sqlList = " select r.* ";
//		String sqlCount = "select count(1) as total ";
//		String sql = "  from role r inner join role_permission  rp on r.id = rp.role_id"
//				+ " where rp.permission_id =  ? and rp.deleted = ? ";
//		arg.add(form.getPermissionId());
//		arg.add(YesNoEnum.No.getValue());
//		String name = form.getName();
//		if (!StringUtils.isEmpty(name)) {
//			sql += " and name like ? ";
//			arg.add("%" + name + "%");
//		}
//		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
//		pd.setTotal(total);
//		if (total == 0) {
//			pd.setArr(new ArrayList<Department>());
//			return pd;
//		}
//		sql += " limit ?,?";
//		arg.add(getBgn(form.getPage(), form.getSize()));
//		arg.add(getEnd(form.getPage(), form.getSize()));
//		List<Role> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(Role.class));
//		pd.setArr(query);
//		return pd;
//	}
//	public Role selectByKey(final Object key) {
//		return roleMapper.selectByPrimaryKey(key);
//	}
//
//	public List<Role> getRolesByEmployeeId(Integer employeeId) {
//		List<EmployeeRole> employeeRoles = employeeRoleService.getEmployeeRolesByEmployeeId(employeeId);
//		if (employeeRoles.size() == 0) {
//			return new ArrayList<>();
//		}
//		List<Integer> roleIds = employeeRoles.stream().map(EmployeeRole::getRoleId).collect(Collectors.toList());
//		List<Role> roles = getAllRoles().stream().filter(r -> roleIds.contains(r.getId())).collect(Collectors.toList());
//		return roles;
//	}
//
////	@CacheEvict(value = "role", key = ROLE_CACHE_KEY)
//	public int insert(Role t) {
//		return roleMapper.insertSelective(t);
//	}
//
////	@CacheEvict(value = "role", key = ROLE_CACHE_KEY)
//	public int updateByPrimaryKey(Role t) {
//		return roleMapper.updateByPrimaryKey(t);
//	}
//
////	@CacheEvict(value = "role", key = ROLE_CACHE_KEY)
//	public int deleteByPrimaryKey(Integer key, ModifyModel modifyModel) {
//		// 软删除
//		Role dbObj = this.roleMapper.selectByPrimaryKey(key);
//		if (dbObj != null) {
//			ModelHelper.buildCreateAndModify(dbObj, modifyModel);
//			dbObj.setDeleted(YesNoEnum.Yes.getValue());
//			return roleMapper.updateByPrimaryKey(dbObj);
//		}
//		return 0;
//	}
//
//	@Cacheable(value = "jq_dc_role", key = "#root.methodName")
//	public List<Role> getAllRoles() {
//		Role r = new Role();
//		r.setDeleted(YesNoEnum.No.getValue());
//		return roleMapper.select(r);
//	}
//}

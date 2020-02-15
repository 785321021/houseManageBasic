package com.coins.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.coins.common.enums.YesNoEnum;
import com.coins.entity.RolePermission;
import com.coins.entity.common.ModifyModel;
import com.coins.mapper.RolePermissionMapper;
import com.coins.utils.ModelHelper;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl{

	private final String ROLE_PERMISSION_CACHE_KEY = "ALL_ROLE_PERMISSION";

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	public List<RolePermission> findPermissionsByRoleIds(List<Integer> roleIds) {
		String sql = "select rp.* from role_permission where role_id in (";
		ArrayList<Object> arg =new ArrayList<Object>();
		for (Integer i : roleIds) {
			sql +=i+",";
		}
		sql= sql.substring(0,sql.length()-1);
		sql += " ) ";
		return jdbc.query(sql, arg.toArray(),new BeanPropertyRowMapper<>(RolePermission.class));
	}
//	@CacheEvict(value = "role_permission", key = ROLE_PERMISSION_CACHE_KEY)
	public void deleteByRoleIdPermissionId(Integer roleId, Integer permissionId, ModifyModel modifyModel) {

		RolePermission record = new RolePermission();

		record.setRoleId(roleId);
		record.setPermissionId(permissionId);

		Optional<RolePermission> RolePermissionEntityOpt = rolePermissionMapper.select(record).stream()
				.findFirst();
		if (RolePermissionEntityOpt.isPresent()) {
			RolePermission RolePermissionEntity = RolePermissionEntityOpt.get();
			ModelHelper.buildCreateAndModify(RolePermissionEntity, modifyModel);
			RolePermissionEntity.setDeleted(YesNoEnum.Yes.getValue());
			rolePermissionMapper.updateByPrimaryKey(RolePermissionEntity);
		}
	}
//	@Cacheable(value = "role_permission", key = ROLE_PERMISSION_CACHE_KEY)
	public List<RolePermission> selectAll() {
		RolePermission record = new RolePermission();
		record.setDeleted(YesNoEnum.No.getValue());
		return rolePermissionMapper.select(record);

	}
//	@CacheEvict(value = "role_permission", key = ROLE_PERMISSION_CACHE_KEY)
	public void insert(RolePermission t, ModifyModel modifyModel) {

		RolePermission record = new RolePermission();
		record.setRoleId(t.getRoleId());
		record.setPermissionId(t.getPermissionId());

		Optional<RolePermission> RolePermissionEntityOpt = rolePermissionMapper.select(record).stream()
				.findFirst();
		if (RolePermissionEntityOpt.isPresent()) {
			RolePermission RolePermissionEntity = RolePermissionEntityOpt.get();
			RolePermissionEntity.setDeleted(YesNoEnum.No.getValue());
			ModelHelper.buildCreateAndModify(RolePermissionEntity, modifyModel);
			rolePermissionMapper.updateByPrimaryKey(RolePermissionEntity);
		} else {
			ModelHelper.buildCreateAndModify(t, modifyModel);
			rolePermissionMapper.insertSelective(t);
		}
	}

//	@CacheEvict(value = "role_permission", key = ROLE_PERMISSION_CACHE_KEY)
	public int deleteByPrimaryKey(Integer key, ModifyModel modifyModel) {
		// 软删除
		RolePermission dbObj = this.rolePermissionMapper.selectByPrimaryKey(key);
		if (dbObj != null) {
			ModelHelper.buildCreateAndModify(dbObj, modifyModel);
			dbObj.setDeleted(YesNoEnum.Yes.getValue());
			return rolePermissionMapper.updateByPrimaryKey(dbObj);
		}
		return 0;
	}

//	@Cacheable(value = "role_permission", key = ROLE_PERMISSION_CACHE_KEY)
	public List<RolePermission> getAll() {
		RolePermission rp = new RolePermission();
		rp.setDeleted(YesNoEnum.No.getValue());
		return rolePermissionMapper.select(rp);
	}
}

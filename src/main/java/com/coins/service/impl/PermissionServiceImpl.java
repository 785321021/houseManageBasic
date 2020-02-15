//package com.coins.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import com.coins.common.enums.AppTypeEnum;
//import com.coins.common.enums.YesNoEnum;
//import com.coins.entity.Permission;
//import com.coins.entity.Role;
//import com.coins.entity.RolePermission;
//import com.coins.entity.common.ModifyModel;
//import com.coins.entity.form.PermissionForm;
//import com.coins.mapper.PermissionMapper;
//import com.coins.utils.ModelHelper;
//import com.google.common.collect.Lists;
//
//@Service
//public class PermissionServiceImpl {
//
//	private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
//
//	@Autowired
//	private RolePermissionServiceImpl rolePermissionService;
//	@Autowired
//	private RoleServiceImpl roleService;
//	@Autowired
//	private PermissionMapper permissionMapper;
//
//	/**
//	 * 获取所有菜单列表
//	 * 
//	 * @param employeeId
//	 * @param appTypeEnum
//	 * @return
//	 */
//	@Cacheable(value = "jq_dc_permission", key = "#employeeId", unless = "#result==null")
//	public List<PermissionForm> getMenusByEmployeeId(Integer employeeId, AppTypeEnum appTypeEnum) {
//		List<Permission> PermissionEntitys = getPermissionsByEmployeeId(employeeId, appTypeEnum).stream()
//				.filter(p -> p.getDisplay() == 1).collect(Collectors.toList());
//		List<Permission> topP = getSubs(PermissionEntitys, 0);
//		List<PermissionForm> permissionModels = Lists.newArrayList();
//		topP.forEach(PermissionEntitys1 -> {
//			PermissionForm top = buildPermissionEntity(PermissionEntitys1, PermissionEntitys);
//			permissionModels.add(top);
//		});
//		return permissionModels;
//	}
////    public PageInfo<Permission> selectPage(SearchModel searchModel) {
////
////        Example example = ExampleBuilder.forClass(PermissionEntity.class)
////                .fromSearchFilters(searchModel.getSearchFilters()).build();
////
////        return PageHelper.startPage(searchModel.getPageNum(), searchModel.getPageSize()
////                , searchModel.getOrderBy())
////                .doSelectPageInfo(
////                        () -> permissionEntityMapper.selectByExample(example)
////                );
////    }
//
////    public List<Permission> select(List<SearchFilter> searchFilters) {
////
////        Example example = ExampleBuilder.forClass(PermissionEntity.class)
////                .fromSearchFilters(searchFilters).build();
////
////        return permissionMapper.selectByExample(example);
////    }
//
//	public Permission selectByKey(final Object key) {
//		return permissionMapper.selectByPrimaryKey(key);
//	}
//
//	public List<PermissionForm> getDisplayMenus() {
//		List<Permission> PermissionEntitys = getAllPermissions().stream().filter(p -> p.getDisplay() == 1)
//				.collect(Collectors.toList());
//		List<Permission> topP = getSubs(PermissionEntitys, 0);
//		List<PermissionForm> permissionModels = Lists.newArrayList();
//		topP.forEach(PermissionEntitys1 -> {
//			PermissionForm top = buildPermissionEntity(PermissionEntitys1, PermissionEntitys);
//			permissionModels.add(top);
//		});
//		return permissionModels;
//	}
//
//	/**
//	 * 所有权限树节点
//	 * 
//	 * @param appTypeEnum
//	 * @return
//	 */
//	public List<PermissionForm> getAllMenus(AppTypeEnum appTypeEnum) {
//		List<Permission> PermissionEntitys = getAllPermissions().stream()
//				.filter(p -> p.getAppId().equals(appTypeEnum.getValue())).collect(Collectors.toList());
//		List<Permission> topP = getSubs(PermissionEntitys, 0);
//		List<PermissionForm> permissionModels = Lists.newArrayList();
//		topP.forEach(PermissionEntitys1 -> {
//			PermissionForm top = buildPermissionEntity(PermissionEntitys1, PermissionEntitys);
//			permissionModels.add(top);
//		});
//		return permissionModels;
//	}
//
//	/**
//	 * 全部权限加入缓存，当对权限进行增删改时去除缓存
//	 * 
//	 * @return
//	 */
//	@Cacheable(value = "jq_dc_permission", key = "#root.methodName", unless = "#result==null")
//	public List<Permission> getAllPermissions() {
//		Permission p = new Permission();
//		p.setDeleted(YesNoEnum.No.getValue());
//		return permissionMapper.select(p);
//	}
//
//	private PermissionForm buildPermissionEntity(Permission permissionEntity, List<Permission> PermissionEntitys) {
//		PermissionForm PermissionModel = new PermissionForm();
//		BeanUtils.copyProperties(permissionEntity, PermissionModel);
//
//		List<Permission> subs = getSubs(PermissionEntitys, permissionEntity.getId());
//
//		if (subs.size() > 0) {
//			PermissionModel.setHasSub(true);
//			List<PermissionForm> subMenus = Lists.newArrayList();
//			subs.forEach(PermissionEntity1 -> {
//				PermissionForm subDept = buildPermissionEntity(PermissionEntity1, PermissionEntitys);
//				subMenus.add(subDept);
//			});
//			PermissionModel.setItems(subMenus);
//		} else
//			PermissionModel.setHasSub(false);
//
//		return PermissionModel;
//
//	}
//
//	private List<Permission> getSubs(List<Permission> allPermissionEntity, int pid) {
//		Stream<Permission> PermissionEntitys = allPermissionEntity.stream().filter(p -> p.getPid() == pid)
//				.sorted((p1, p2) -> p1.getSort().compareTo(p2.getSort()));
//		return PermissionEntitys.collect(Collectors.toList());
//	}
//
//	public List<Permission> selectAll() {
//		Permission example = new Permission();
//		example.setDeleted(YesNoEnum.No.getValue());
//		return permissionMapper.select(example);
//	}
//
//	@CacheEvict(value = "jq_dc_permission")
//	public int insert(Permission t) {
//		return permissionMapper.insertSelective(t);
//	}
//
//	@CacheEvict(value = "jq_dc_permission")
//	public int updateByPrimaryKey(Permission t) {
//		return permissionMapper.updateByPrimaryKeySelective(t);
//	}
//
//	@CacheEvict(value = "jq_dc_permission")
//	public void deleteByPrimaryKey(Integer key, ModifyModel modifyModel) {
//		// 软删除
//		Permission dbObj = this.permissionMapper.selectByPrimaryKey(key);
//		if (dbObj != null) {
//			delete(dbObj, modifyModel);
//		}
//	}
//
//	@CacheEvict(value = "jq_dc_permission")
//	private void delete(Permission PermissionEntity, ModifyModel modifyModel) {
//
//		Permission record = new Permission();
//		record.setPid(PermissionEntity.getId());
//
//		List<Permission> subs = permissionMapper.select(record);
//		subs.stream().forEach(PermissionEntity1 -> {
//			delete(PermissionEntity1, modifyModel);
//		});
//		ModelHelper.buildCreateAndModify(PermissionEntity, modifyModel);
//		PermissionEntity.setDeleted(YesNoEnum.Yes.getValue());
//		permissionMapper.updateByPrimaryKey(PermissionEntity);
//	}
//
//	public List<Permission> getPermissionsByEmployeeId(Integer employeeId, AppTypeEnum appTypeEnum) {
//		List<Role> roles = roleService.getRolesByEmployeeId(employeeId);
//		if (roles.size() == 0) {
//			return new ArrayList<>();
//		}
//		List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
//		List<RolePermission> rolePermissions = rolePermissionService.getAll().stream()
//				.filter(rp -> roleIds.contains(rp.getRoleId())).collect(Collectors.toList());
//		if (rolePermissions.size() == 0) {
//			return new ArrayList<>();
//		}
//		List<Integer> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId)
//				.collect(Collectors.toList());
//		List<Permission> permissions = getAllPermissions();
//		return permissions.stream()
//				.filter(p -> permissionIds.contains(p.getId()) && p.getAppId().equals(appTypeEnum.getValue()))
//				.collect(Collectors.toList());
//	}
//
//}

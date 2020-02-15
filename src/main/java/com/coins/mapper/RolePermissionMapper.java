package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.RolePermission;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}

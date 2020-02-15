package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Permission;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}

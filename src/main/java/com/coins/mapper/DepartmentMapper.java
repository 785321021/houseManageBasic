package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Department;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
}

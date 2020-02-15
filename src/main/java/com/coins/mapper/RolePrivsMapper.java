package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.RolePrivs;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface RolePrivsMapper extends BaseMapper<RolePrivs>,MySqlMapper<RolePrivs> {
}

package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Admins;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface AdminsMapper extends BaseMapper<Admins>,MySqlMapper<Admins>{
}

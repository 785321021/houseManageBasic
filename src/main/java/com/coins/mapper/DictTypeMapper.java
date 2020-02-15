package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Admins;
import com.coins.entity.DictType;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface DictTypeMapper extends BaseMapper<DictType>,MySqlMapper<DictType>{
}

package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Logs;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface LogsMapper extends BaseMapper<Logs> {
}

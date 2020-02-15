package com.coins.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.PidLogs;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface PidLogsMapper extends BaseMapper<PidLogs> {
}

package com.coins.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.Role;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface RoleMapperForOldJuqi extends BaseMapper<Role> {
    /**
     * @param username 用户名
     * @return 统计结果
     */
    int countByName(String name);
}

package com.coins.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.coins.entity.RoleUser;

import io.lettuce.core.dynamic.annotation.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser>,MySqlMapper<RoleUser>{
	@Delete("delete from role_users where user_id = #{user_id}")
	int deleteByUserid(@Param("user_id") Integer user_id);
}

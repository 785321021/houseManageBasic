package com.coins.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.coins.entity.Menus;

import io.lettuce.core.dynamic.annotation.Param;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface MenusMapper extends BaseMapper<Menus> {
	@Select("select * from menus where display = 1 and id in (select distinct menu_id from role_privs where  role_id in (select role_id from role_users where user_id = #{user_id} )) order by id")
	List<Menus> selectByUserId(@Param("user_id") Integer user_id);
	@Select("select * from menus where  and id in (select distinct menu_id from role_privs where  role_id in (select role_id from role_users where user_id = #{user_id} )) order by id")
	List<Menus> selectByUserIdTree(@Param("user_id") Integer user_id);
	

}

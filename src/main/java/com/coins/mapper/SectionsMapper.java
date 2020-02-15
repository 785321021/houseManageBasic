package com.coins.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.coins.entity.Sections;

import io.lettuce.core.dynamic.annotation.Param;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface SectionsMapper extends BaseMapper<Sections> {
	List<Sections> getList(Map data);

	@Select("select count(1) from sections where name like CONCAT('%',#{name},'%')")
	int getCount(@Param("name") String q);
}

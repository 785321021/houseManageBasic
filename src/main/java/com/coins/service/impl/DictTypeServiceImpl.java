package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.coins.common.enums.YesNoEnum;
import com.coins.entity.Admins;
import com.coins.entity.DictType;
import com.coins.entity.RoleUser;
import com.coins.entity.common.PageData;
import com.coins.entity.form.AdminsForm;
import com.coins.entity.form.DictForm;
import com.coins.entity.form.DictTypeForm;
import com.coins.mapper.DictTypeMapper;
import com.coins.mapper.RoleUserMapper;
import com.coins.utils.ObjectConvertUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysql.cj.util.StringUtils;

@Transactional
@Service
public class DictTypeServiceImpl extends BaseServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(DictTypeServiceImpl.class);
	@Autowired
	private DictTypeMapper dictTypeMapper;

//	private final String CACHE ="jq_dc_umsg";

	public PageData getList(DictTypeForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from sys_dict_type where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		if (!StringUtils.isNullOrEmpty(form.getName())) {
			sql += " and (NAME_CN like ? or NAME_EN like ?) ";
			arg.add("%" + form.getName() + "%");
			arg.add("%" + form.getName() + "%");
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<DictTypeForm>());
			return pd;
		}
		sql += " order by CREATE_TIME desc limit ?,?";
		arg.add(getBgn(form.getPage(), form.getSize()));
		arg.add(getEnd(form.getPage(), form.getSize()));
		List<DictTypeForm> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(DictTypeForm.class));
		pd.setArr(query);
		return pd;
	}

	public List<DictType> getAll(){
		return dictTypeMapper.selectAll();
	}

	public void addOrUpdate(DictTypeForm dictType) {
		DictType ad = ObjectConvertUtil.convert(dictType, DictType.class);
		ad.setUPDATE_TIME(new Date());
		if(dictType.getId()==null) {//新增
			System.out.println("zeng");
			ad.setCREATE_TIME(new Date());
			String id = UUID.randomUUID().toString();
			ad.setId(id);
			dictTypeMapper.insert(ad);
		}else { //修改
			dictTypeMapper.updateByPrimaryKeySelective(ad);
		}
	}

	public DictTypeForm getInfo(String string) {
		DictType dictType = dictTypeMapper.selectByPrimaryKey(string);
		DictTypeForm convert = ObjectConvertUtil.convert(dictType, DictTypeForm.class);
//		convert.setRoleIds(getAllRoleIds(dictType.getId()));
		return convert;
				
	}

	public int delete(String id) {
		return dictTypeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据类型ID查询字典内容
	 * @param form
	 * @return
	 */
	public PageData geDictList(DictForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from sys_dict where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		if (!StringUtils.isNullOrEmpty(form.getDict_type_id())) {
			sql += " and DICT_TYPE_ID = ? ";
			arg.add(form.getDict_type_id());
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<DictTypeForm>());
			return pd;
		}
		sql += " order by SORT asc ";
		System.out.println(sql);
		List<DictForm> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(DictForm.class));
		pd.setArr(query);
		return pd;
	}
	
	/**
	 * 根据id数组查询字典集合
	 * @param ids
	 * @return
	 */
	public Map<String,List<DictForm>> geDictListByIds(List<String> ids){
		Map<String,List<DictForm>> dictTypeMap = new HashMap<>();
		if(!ids.isEmpty()) {
			Map map = new HashMap<>();
			for (String typeId : ids) {
				ArrayList<Object> arg = new ArrayList<Object>();
				//查询字典类型
				String sql_1 = "select NAME_EN from sys_dict_type where id ='"+typeId+"'";
				try {
					Map<String, Object> dict_type = jdbc.queryForMap(sql_1);
					String dict_name = dict_type.get("NAME_EN").toString();
					//查询字典内容
					String sql = "select label, value, sort from sys_dict where DICT_TYPE_ID = ?";
					arg.add(typeId);
					sql += " order by SORT asc ";
					List<DictForm> query = jdbc.query(sql, arg.toArray(), new BeanPropertyRowMapper<>(DictForm.class));
					dictTypeMap.put(dict_name, query);
				} catch (Exception e) {
					continue;
				}
				
//				for (DictForm data : query) {
//					map.put(data.getValue(), data.getLabel());
//				}
//				dictTypeMap.put(dict_name+"Dict", map);
				
			}
		}
		return dictTypeMap;
		
	}
}

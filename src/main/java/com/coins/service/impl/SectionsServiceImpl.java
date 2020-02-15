package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coins.common.enums.YesNoEnum;
import com.coins.entity.Sections;
import com.coins.entity.common.PageData;
import com.coins.entity.form.SectionsForm;
import com.coins.mapper.SectionsMapper;
import com.mysql.cj.util.StringUtils;

@Transactional
@Service
public class SectionsServiceImpl extends BaseServiceImpl {
	@Autowired
	private SectionsMapper sectionsMapper;

	public PageData selectPage(SectionsForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from sections where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		String name = form.getName();
		if (!StringUtils.isNullOrEmpty(name)) {
			sql += " and name like ? ";
			arg.add("%" + name + "%");
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<Sections>());
			return pd;
		}
		sql += "limit ?,?";
		arg.add(getBgn(form.getPage(), form.getSize()));
		arg.add(getEnd(form.getPage(), form.getSize()));
		List<Sections> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(Sections.class));
		pd.setArr(query);
		return pd;
	}

	public Sections getInfo(int id) {
		return sectionsMapper.selectByPrimaryKey(id);
	}

	public void addOrUpdate(Sections section) {
		if (null == section.getId() || 0 == section.getId()) {// 新增
			section.setCreated_at(new Date());
			section.setStatus(YesNoEnum.Yes.getValue());
			sectionsMapper.insert(section);
		} else {// 修改
			section.setUpdated_at(new Date());
			sectionsMapper.updateByPrimaryKeySelective(section);
		}
	}

	public int delete(Integer id) {
		return sectionsMapper.deleteByPrimaryKey(id);
	}
	
	public List<Sections> getAll() {
		return sectionsMapper.selectAll();
	}
}

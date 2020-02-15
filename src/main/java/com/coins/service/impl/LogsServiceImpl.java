package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coins.entity.Logs;
import com.coins.entity.common.PageData;
import com.coins.entity.form.LogsForm;
import com.coins.mapper.LogsMapper;
import com.coins.utils.DateUtils;
import com.mysql.cj.util.StringUtils;

/**
 * 数据字典service
 */
@Service
public class LogsServiceImpl extends BaseServiceImpl {

	@Autowired
	private LogsMapper logsMapper;

	public PageData selectPage(LogsForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from logs where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		if(null != form.getAdminId()) {
			sql += " and admin_id = ? ";
			arg.add(form.getAdminId());
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<LogsForm>());
			return pd;
		}
		sql += " order by created_at desc limit ?,?";
		arg.add(getBgn(form.getPage(), form.getSize()));
		arg.add(getEnd(form.getPage(), form.getSize()));
		List<LogsForm> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(LogsForm.class));
		pd.setArr(query);
		return pd;
	}

	public Logs getById(Integer id) {
		return logsMapper.selectByPrimaryKey(id);

	}

	public List<Logs> selectAll() {
		return logsMapper.selectAll();
	}

	@Transactional
	public void save(Logs logs) {
		logsMapper.insert(logs);
	}

	public int deleteByPrimaryKey(Integer id) {
		return logsMapper.deleteByPrimaryKey(id);
	}

	public void clear() {
		String sdata = DateUtils.formatDate(new Date(System.currentTimeMillis() - (7L * 24L * 3600000L)),
				"yyyy-MM-dd HH:mm:ss");
		jdbc.update(" delete from logs where created_at <= ?", new Object[] { sdata });
	}
}

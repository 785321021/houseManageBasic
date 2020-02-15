package com.coins.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.coins.common.enums.YesNoEnum;
import com.coins.entity.Admins;
import com.coins.entity.RoleUser;
import com.coins.entity.common.PageData;
import com.coins.entity.form.AdminsForm;
import com.coins.mapper.AdminsMapper;
import com.coins.mapper.RoleUserMapper;
import com.coins.utils.ObjectConvertUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysql.cj.util.StringUtils;

@Transactional
@Service
public class AdminsServiceImpl extends BaseServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(AdminsServiceImpl.class);
	@Autowired
	private AdminsMapper adminsMapper;
	@Autowired
	private RoleUserMapper roleUserMapper;
	@Autowired
	private RedisService redisService;
	
	private final String CACHE ="jq_dc_umsg";

	public PageData getList(AdminsForm form) {
		PageData pd = new PageData();
		String sqlList = " select * ";
		String sqlCount = "select count(1) as total ";
		String sql = "  from admins where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		if (!StringUtils.isNullOrEmpty(form.getName())) {
			sql += " and name like ? ";
			arg.add("%" + form.getName() + "%");
		}
		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
		pd.setTotal(total);
		if (total == 0) {
			pd.setArr(new ArrayList<AdminsForm>());
			return pd;
		}
		sql += " order by id asc limit ?,?";
		arg.add(getBgn(form.getPage(), form.getSize()));
		arg.add(getEnd(form.getPage(), form.getSize()));
		List<AdminsForm> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(AdminsForm.class));
		pd.setArr(query);
		return pd;
	}

	/**
	 * 新增/修改用户
	 * 
	 * @param admins
	 */
	public void addOrUpdate(AdminsForm admins) {
		Admins ad = ObjectConvertUtil.convert(admins, Admins.class);
		ad.setUpdated_at(new Date());
		if(admins.getId()==null) {//新增
			ad.setStatus(YesNoEnum.Yes.getValue());
			ad.setCreated_at(new Date());
			ad.setPassword(DigestUtils.md5DigestAsHex(admins.getPassword().getBytes()));
			adminsMapper.insert(ad);
		}else { //修改
			adminsMapper.updateByPrimaryKeySelective(ad);
			// 先删除再修改
			roleUserMapper.deleteByUserid(ad.getId());
		}
		Integer id = ad.getId();
		ArrayList<RoleUser> ru = Lists.newArrayList();
		admins.getRoleIds().stream().forEach(rid -> {
			ru.add(new RoleUser(rid, id));
		});
		if(null!=ru && ru.size()>0) {
			roleUserMapper.insertList(ru);	
		}
	}

	public AdminsForm getInfo(int id) {
		Admins admins = adminsMapper.selectByPrimaryKey(id);
		AdminsForm convert = ObjectConvertUtil.convert(admins, AdminsForm.class);
		convert.setRoleIds(getAllRoleIds(admins.getId()));
		return convert;
				
	}

	public int delete(Integer id) {
		return adminsMapper.deleteByPrimaryKey(id);
	}

	public int editPassword(Admins admins) {
		admins.setPassword(DigestUtils.md5DigestAsHex(admins.getPassword().getBytes()));
		admins.setUpdated_at(new Date());
		removeUserMsg(admins);
		return adminsMapper.updateByPrimaryKeySelective(admins);
	}
	
	public List<Admins> getLoginInfo(String phone,String pwd) {
		Admins a = new Admins();
		a.setPhone(phone);
		a.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
		return adminsMapper.select(a);
	}
	
	public void selfEditInfo(AdminsForm form) {
		form.setUpdated_at(new Date());
		adminsMapper.updateByPrimaryKeySelective(form);
		Admins convert = ObjectConvertUtil.convert(form,Admins.class);
		removeUserMsg(convert);
	}
	/**
	 * 登录成功,更新用户信息， 需要记录 redis  并更新 amdins 表数据
	 * @param am
	 * @param token
	 */
	public void saveUserMsg(Admins am, String token){
		adminsMapper.updateByPrimaryKeySelective(am);
		List<Integer> ru = getAllRoleIds(am.getId());
		HashMap<String ,Object> mm =Maps.newHashMap();
		String privsSql = " select url from role_privs where 1=1 ";
		ArrayList<Object> arg = new ArrayList<Object>();
		if (null != ru && ru.size() > 0) {
			privsSql += " and role_id in ( ";
			for (Integer ii : ru) {
				privsSql += " ? ,";
				arg.add(ii);
			}
			privsSql = privsSql.substring(0, privsSql.length() - 1) + " ) ";
		}
		//所有url
		List<String> rpUrl = jdbc.queryForList(privsSql, arg.toArray(),String.class);
		mm.put("user", am);
		mm.put("token", token);
		mm.put("allRolesId", ru);
		mm.put("allPrivsUrl", rpUrl);
		if(redisService.hHasKey(CACHE, am.getId()+"msg")) {
			redisService.hdel(CACHE, am.getId()+"msg");
		}
		redisService.hset(CACHE,am.getId()+"msg", mm,24L*3600L );
	}

	public void removeUserMsg(Admins ad) {
		if(redisService.hHasKey(CACHE, ad.getId()+"msg")) {
			redisService.hdel(CACHE, ad.getId()+"msg");
		}
	}
	//所有角色
	public List<Integer> getAllRoleIds (Integer adminId){
		return jdbc.queryForList(" select distinct role_id from role_users where user_id = ? " ,new Object[] {adminId},Integer.class);
	}
	public List<Admins> getAll(){
		return adminsMapper.selectAll();
	}
}

//package com.coins.service.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.DigestUtils;
//
//import com.coins.common.enums.YesNoEnum;
//import com.coins.entity.Admins;
//import com.coins.entity.House;
//import com.coins.entity.RoleUser;
//import com.coins.entity.common.PageData;
//import com.coins.entity.form.AdminsForm;
//import com.coins.entity.form.HouseForm;
//import com.coins.mapper.HouseMapper;
//import com.coins.mapper.RoleUserMapper;
//import com.coins.utils.ObjectConvertUtil;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.mysql.cj.util.StringUtils;
//
//@Transactional
//@Service
//public class HouseServiceImpl extends BaseServiceImpl {
//	private static final Logger log = LoggerFactory.getLogger(HouseServiceImpl.class);
//	@Autowired
//	private HouseMapper houseMapper;
//	@Autowired
//	private RoleUserMapper roleUserMapper;
//	@Autowired
//	private RedisService redisService;
//	
//	private final String CACHE ="jq_dc_umsg";
//
//	public PageData getList(HouseForm form) {
//		PageData pd = new PageData();
//		String sqlList = " select * ";
//		String sqlCount = "select count(1) as total ";
//		String sql = "  from house_info where 1=1 ";
//		ArrayList<Object> arg = new ArrayList<Object>();
////		if (!StringUtils.isNullOrEmpty(form.getName())) {
////			sql += " and name like ? ";
////			arg.add("%" + form.getName() + "%");
////		}
//		int total = jdbc.queryForObject(sqlCount + sql, arg.toArray(), Integer.class);
//		pd.setTotal(total);
//		if (total == 0) {
//			pd.setArr(new ArrayList<HouseForm>());
//			return pd;
//		}
//		sql += " order by number asc limit ?,?";
//		arg.add(getBgn(form.getPage(), form.getSize()));
//		arg.add(getEnd(form.getPage(), form.getSize()));
//		List<HouseForm> query = jdbc.query(sqlList + sql, arg.toArray(), new BeanPropertyRowMapper<>(HouseForm.class));
//		pd.setArr(query);
//		return pd;
//	}
//
//	public List<House> getAll(){
//		return houseMapper.selectAll();
//	}
////	
////	public void addOrUpdate(HouseForm houseInfo) {
////		House ad = ObjectConvertUtil.convert(houseInfo, House.class);
//////		ad.setUpdated_at(new Date());
////		if(admins.getId()==null) {//新增
////			ad.setStatus(YesNoEnum.Yes.getValue());
////			ad.setCreated_at(new Date());
////			ad.setPassword(DigestUtils.md5DigestAsHex(admins.getPassword().getBytes()));
////			adminsMapper.insert(ad);
////		}else { //修改
////			adminsMapper.updateByPrimaryKeySelective(ad);
////			// 先删除再修改
////			roleUserMapper.deleteByUserid(ad.getId());
////		}
////		Integer id = ad.getId();
////		ArrayList<RoleUser> ru = Lists.newArrayList();
////		admins.getRoleIds().stream().forEach(rid -> {
////			ru.add(new RoleUser(rid, id));
////		});
////		if(null!=ru && ru.size()>0) {
////			roleUserMapper.insertList(ru);	
////		}
////	}
//}

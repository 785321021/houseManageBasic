package com.coins.controller.home;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coins.entity.Admins;
import com.coins.entity.DictType;
import com.coins.entity.common.PageData;
import com.coins.entity.form.AdminsForm;
import com.coins.entity.form.DictForm;
import com.coins.entity.form.DictTypeForm;
import com.coins.entity.resp.RespEntity;
import com.coins.service.impl.DictTypeServiceImpl;

@RestController
@RequestMapping("/c-api/dictType")
public class DictController extends BaseController {

	@Autowired
	private DictTypeServiceImpl dictTypeService;
	/**
	 * 查询字典类型列表
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RespEntity list(@RequestBody DictTypeForm form) {
		PageData pd = new PageData();
		System.out.println("333333");
		try {
			if(null == form.getPage()) {
				return success("查询字典成功",dictTypeService.getAll());
			}
			pd = dictTypeService.getList(form);
			List<DictTypeForm> arr = pd.getArr();
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("total", pd.getTotal());
			data.put("list", arr);
			System.out.println(data);
			return success("查询字典成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}
	/**
	 * 保存字典类型信息
	 * @param form
	 * @return
	 */
	@RequestMapping(value = { "/create", "/editinfo" }, method = RequestMethod.POST)
	public RespEntity create(@RequestBody DictTypeForm form) {
		try {
			System.out.println("create");
			dictTypeService.addOrUpdate(form);
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}
	/**
	 * 查询字典类型详情
	 * @param form
	 * @return
	 */
	@RequestMapping("/detail")
	public RespEntity detail(@RequestBody DictTypeForm form) {
		try {
			System.out.println("detail"+form.getId());
			DictType sec = dictTypeService.getInfo(form.getId());
			return success( "查询详情成功...", sec);
		} catch (Exception e) {
			return warn(e);
		}
	}
	/**
	 * 删除字典类型
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public RespEntity remove(@RequestBody DictTypeForm form) {
		try {
			System.out.println("remove"+form.getId());
			dictTypeService.delete(form.getId());
			return SUCCESS;
		} catch (Exception e) {
			return warn(e);
		}
	}
	
	
	/**
	 * 根据类型ID查询字典内容
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/dictList", method = RequestMethod.POST)
	public RespEntity dictList(@RequestBody DictForm form) {
		PageData pd = new PageData();
		System.out.println("4444");
		try {
			if(null != form.getDict_type_id_list() && !form.getDict_type_id_list().isEmpty()) {
				Map<String,List<DictForm>> res = dictTypeService.geDictListByIds(form.getDict_type_id_list());
				return success("查询字典成功", res);
			}
			pd = dictTypeService.geDictList(form);
			List<DictForm> arr = pd.getArr();
			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("total", pd.getTotal());
			data.put("list", arr);
			System.out.println(data);
			return success("查询字典成功", data);
		} catch (Exception e) {
			return warn(e);
		}
	}
	
	
}

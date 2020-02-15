//package com.coins.controller.home;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.coins.entity.common.PageData;
//import com.coins.entity.form.AdminsForm;
//import com.coins.entity.form.HouseForm;
//import com.coins.entity.resp.RespEntity;
//import com.coins.service.impl.HouseServiceImpl;
//
//@RestController
//@RequestMapping("/c-api/house")
//public class HouseController extends BaseController {
//
//	@Autowired
//	private HouseServiceImpl houseService;
//	/**
//	 *  查询房屋信息
//	 * @param form
//	 * @return
//	 */
//	@RequestMapping(value = "/list", method = RequestMethod.POST)
//	public RespEntity list(@RequestBody HouseForm form) {
//		PageData pd = new PageData();
//		System.out.println("22222222222");
//		try {
//			if(null == form.getPage()) {
//				return success("查询售房列表成功",houseService.getAll());
//			}
//			pd = houseService.getList(form);
//			List<HouseForm> arr = pd.getArr();
//			LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
//			data.put("total", pd.getTotal());
//			data.put("list", arr);
//			System.out.println(data);
//			return success("查询售房列表成功", data);
//		} catch (Exception e) {
//			return warn(e);
//		}	
//	}
//	
//	/**
//	 * 添加或修改房屋信息
//	 * @param form
//	 * @return
//	 */
//	@RequestMapping(value = { "/create", "/editinfo" }, method = RequestMethod.POST)
//	public RespEntity create(@RequestBody HouseForm form) {
//		try {
////			houseService.addOrUpdate(form);
//			return SUCCESS;
//		} catch (Exception e) {
//			return warn(e);
//		}
//	}
//	
//	
//}

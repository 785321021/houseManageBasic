package com.coins.utils.exportData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.common.base.Strings;

public class ExportOrder {
	
	public  static void test() {
		String path = "C:/Users/Administrator/Desktop/正式服点餐订单数据备份/1.0/order.csv";
		StringBuffer sb  = new StringBuffer("INSERT INTO `order`(`bs_id`, `bs_no`, `ts_no`, `corp_id`, `corp_code`, `corp_name`, `point_code`, `point_name`, `customer_name`, `customer_phone`, `member_card_level`, `member_card_code`, `people_quantity`, `man_quantity`, `woman_quantity`, `elder_quantity`, `middle_quantity`, `child_quantity`, `sub_price`, `pay_price`, `comment_level`, `kt_time`, `pay_time`, `shift`, `corp_emp_id`, `semp_code`, `semp_id`, `emp_code`, `emp_id`, `remark_id`, `status`, `creator_id`, `creator_name`, `created_time`, `modifier_id`, `modifier_name`, `modified_time`) VALUES");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			StringBuffer sbInner = new StringBuffer();
			String line ;
			int num =0;
			while ((line = br.readLine()) != null) {
				num ++;
				if (!Strings.isNullOrEmpty(line) && num<10000) {
//					String[] split = line.split(",");
					System.out.println(line);
					sbInner.append("("+line+"),");
				}
				if(!Strings.isNullOrEmpty(line) && num==10000) {
					sbInner.append("("+line+")");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		test();
	}
}

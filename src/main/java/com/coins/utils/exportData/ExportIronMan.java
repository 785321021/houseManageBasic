package com.coins.utils.exportData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExportIronMan {
	public  static void test() {
		String path = "C:/Users/Administrator/Desktop/局气铁瓷俱乐部(1)(1).csv";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gb2312"));
			String line;
			while ((line = br.readLine()) != null) {
				if (!Strings.isNullOrEmpty(line)) {
					String[] split = line.split(",");
					System.out.println(line);
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

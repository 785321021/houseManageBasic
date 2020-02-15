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

public class ExportTasteByCorp {
	public static void main(String[] args) {
		String path = "C:/Users/Administrator/Desktop/工作簿1.csv";
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"gb2312")) 
		) {
			String line;
			LinkedHashMap<String,List<Integer>> dishMap = Maps.newLinkedHashMap();
			int i =0;
			while ((line = br.readLine()) != null) {
				if(!Strings.isNullOrEmpty(line)&&i>0) {
					String[] split = line.split(",");
					String key = split[0];
					List<Integer> list = Lists.newArrayList();
					for(int j = 1;j<split.length;j++) {
						String flag = split[j];
						if(!Strings.isNullOrEmpty(flag)) {
							list.add(j);
							}
					}
					dishMap.put(key, list);
				}
				i++;
			}
			System.out.println(dishMap);
			System.out.println(dishMap.size());
		} catch (Exception e) {
			e.printStackTrace();

		} 

	}
}

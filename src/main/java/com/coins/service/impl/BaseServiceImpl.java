package com.coins.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseServiceImpl {
	
	@Autowired
	JdbcTemplate jdbc;
	
	protected int getBgn(int page,int size) {
		return (page-1)*size;
	}
	
	protected int getEnd(int page,int size) {
		return size;
	}
	
}

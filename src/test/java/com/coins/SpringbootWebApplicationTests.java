package com.coins;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class SpringbootWebApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(SpringbootWebApplicationTests.class);

//    @Autowired
//    private RoleService roleService;
	@Autowired
	JdbcTemplate jdbc;

	@Test
	public void contextLoads() throws Exception {
//		final Role role = roleService.saveOrUpdate(new Role(5, "u5",1));
//        log.info("[saveOrUpdate] - [{}]", role);
//        final Role role1 = roleService.get(5);
//        log.info("[get] - [{}]", role1);
//        roleService.delete(5);
//        jdbc.query("select * from operate_log where created_time> ?",new Object[] {"2018-02-11"},new BeanPropertyRowMapper(OperateLog.class));
//		redisLIst();

	}

}

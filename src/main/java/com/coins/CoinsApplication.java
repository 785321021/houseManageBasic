package com.coins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@RestController
@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
@EnableCaching
@EnableScheduling
@MapperScan("com.coins.mapper")
public class CoinsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}
}
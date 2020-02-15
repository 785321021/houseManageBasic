package com.coins.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:crypt.properties")
@ConfigurationProperties(prefix = "crypt")
public class CryptProperties {
	private static String des;
	private static String aes;
	
	public static String getDes() {
		return des;
	}
	public void setDes(String des) {
		CryptProperties.des = des;
	}
	public static String getAes() {
		return aes;
	}
	public void setAes(String aes) {
		CryptProperties.aes = aes;
	}
}

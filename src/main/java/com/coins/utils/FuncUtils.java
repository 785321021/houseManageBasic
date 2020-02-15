package com.coins.utils;

import java.util.Random;
import java.util.UUID;
/*
 * 一些常用的方法，放到这里来
 */
public class FuncUtils {
	/*
	 * length用户要求产生字符串的长度
	 */
	public static String getRandomString(final int length){
	     final String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     final Random random = new Random();
	     final StringBuffer sb = new StringBuffer();
	     for(int i=0;i<length;i++){
	       final int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	/**  
     * 以UUID的策略生成一个长度为32的字符串，在同一时空中具有唯一性。  
     * @return UUID128位长度字符串  
     */    
    public static String getUUIDString() {    
        String id = UUID.randomUUID().toString();    
        id = id.replace("-", "");    
        return id;    
    } 
}

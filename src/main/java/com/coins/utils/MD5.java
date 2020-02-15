package com.coins.utils;

import java.security.MessageDigest;

public class MD5 {
    public static void main(String[] args) {
        System.err.println(md5Encode("appsecret=19529988-3048-4C2C-9674-B29DB36AC56F&params={\"rt\":1510554231}"));
        System.err.println(encoderByMd5("appsecret=19529988-3048-4C2C-9674-B29DB36AC56F&params={\"rt\":1510554231}"));
    }

    /***
     * MD5加密 生成32位md5码
     *
     * @param inStr
     *            待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
        try {
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                return "";
            }

            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String authHash(String password, String salt) {
        String str = password + salt;
        return md5Encode(str);
    }

    public static String encoderByMd5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

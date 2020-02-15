package com.coins.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class HttpRequestHelper {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestHelper.class);
	private static final int HTTP_OK = 200;
	private static final int TIME_OUT = 1000 * 6; // 超时
	private static final String METHOD_GET = "GET";
    private static String httpIps="[{'http':'14.153.54.239:8123'},{'http':'222.187.226.161:808'},{'http':'124.134.58.106:8118'},{'http':'115.46.147.226:8123'},{'http':'124.79.240.118:8118'},{'http':'182.88.15.170:8123'},{'http':'42.85.211.101:8118'},{'http':'175.5.48.48:8118'},{'http':'114.241.164.113:8118'},{'http':'117.78.37.198:8000'},{'http':'101.86.86.101:8118'},{'http':'58.243.226.144:808'},{'http':'60.5.143.213:80'}]";
    private static String[] userAents={"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11", 
                                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
                                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)", 
                                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)\"", 
                                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 LBBROWSER", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)", 
                                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)", 
                                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; 360SE)", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)", 
                                        "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1", 
                                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1", 
                                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)", 
                                        "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0", 
                                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)", 
                                        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:16.0) Gecko/20121026 Firefox/16.0", 
                                        "Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5", 
                                        "Mozilla/5.0 (Linux; U; Android 2.2.1; zh-cn; HTC_Wildfire_A3333 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1", 
                                        "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1"
                                      };

    /**
     *
     * @param httpUrl
     * @param method
     * @param parames
     * @param property
     * @return
     */
    public static String request(String httpUrl, String method, Map<String, String> parames, Map<String, String> property) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toUpperCase());
            if (property != null) {
                for (Entry<String, String> pair : property.entrySet()) {
                    connection.setRequestProperty(pair.getKey(), pair.getValue());
                }
            }
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            if (parames != null) {
                connection.setDoOutput(true);
            }
            connection.setReadTimeout(2000);
            connection.setConnectTimeout(2000);
            connection.connect();
            if (parames != null) {
                String paramesStr = getUrlParamsByMap(parames);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(paramesStr);
                out.flush();
                out.close();
            }
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            int intRead;
            while ((intRead = reader.read()) != -1) {
                sbf.append((char) intRead);
            }

            reader.close();
            result = sbf.toString();
        } catch (SocketTimeoutException ex) {
            logger.warn("url:".concat(httpUrl).concat(", 访问失败:").concat(ex.getMessage()), ex);
        } catch (Exception e) {
            logger.error("url:".concat(httpUrl).concat(", 访问出错:").concat(e.getMessage()), e);
        }
        return result;
    }

    /**
     *
     * @param httpUrl
     * @param postBody
     * @param property
     * @return
     */
    public static String post(String httpUrl, String postBody, Map<String, String> property) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            if (property != null) {
                for (Entry<String, String> pair : property.entrySet()) {
                    connection.setRequestProperty(pair.getKey(), pair.getValue());
                }
            }
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            if (postBody != null) {
                connection.setDoOutput(true);
            }

            connection.connect();
            if (postBody != null) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(postBody);
                out.flush();
                out.close();
            }
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            int intRead;
            while ((intRead = reader.read()) != -1) {
                sbf.append((char) intRead);
            }

            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            if (logger != null) {
                logger.error("url:".concat(httpUrl).concat(",访问出错:").concat(e.getMessage()), e);
            }
        }
        return result;
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
        	s = s.substring(0,s.lastIndexOf("&"));
        }
        return s;
    }

    /**
     * 将url参数转换成map
     * @param param
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<>(0);
        if (param == null || param.trim().length() == 0) {
            return null;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 发送HttpPost请求
     *
     * @param strURL 服务地址
     * @param params json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String postJson(String strURL, String params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000000);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }
    /**
   	 * 以Get方式模拟Http请求
   	 */
   	public static String httpGet(String urlStr,String charset) {
   		URL url = null;
   		HttpURLConnection conn = null;
   		InputStream inStream = null;
   		String response = "{\"status\":false,\"message\":\"请求异常\"}";
   		try {
   			url = new URL(urlStr);
   			conn = (HttpURLConnection) url.openConnection();
   			conn.setDoInput(true);
   			conn.setConnectTimeout(TIME_OUT);// 请求超时时间
   			conn.setReadTimeout((TIME_OUT * 10));// 读取超时时间
   			conn.setRequestMethod(METHOD_GET);
   			conn.setRequestProperty("Charset", charset);
   			conn.setRequestProperty("Content-Encoding", charset);
   			conn.setRequestProperty("accept", "*/*");
//   			conn.setRequestProperty("User-Agent",
//   					"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");

            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
            
   			conn.connect();
   			// System.out.println("====="+conn.getHeaderField("Content-Type"));
   			int responseCode = conn.getResponseCode();
   			if (responseCode == HTTP_OK) {
   				inStream = conn.getInputStream();
   				response = getResponse(inStream);
   			} else {
   				response = "{\"status\":false,\"message\":\"请求异常码[" + responseCode + "]\"}";
   				// response = "返回码：" + responseCode;
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		} finally {
   			conn.disconnect();
   		}
   		return response;
   	}
   	private static String getResponse(InputStream inStream) throws IOException {
   		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   		int len = -1;
   		// 下面的read方法每次读取一个字节，并返回这个字节的int类型
   		while ((len = inStream.read()) != -1) {
   			// 每次写入一个字节，out对象会自动创建一个反冲区，并自动增加大小
   			outputStream.write(len);
   		}
   		return outputStream.toString("utf-8");
   	}
    /**
     * 动态ipGet
     */
    public static String httpDynamicIpGet(String urlStr,String charset) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream inStream = null;
        String response = "{\"status\":false,\"message\":\"请求异常\"}";
        try {
            System.setProperty("http.proxySet", "true");
            JSONObject httpObject = JSONArray.parseArray(httpIps).getJSONObject(new Double(Math.random()*13).intValue());
            System.setProperty("http.proxyHost", httpObject.getString("http").split(":")[0]);
            System.setProperty("http.proxyPort", httpObject.getString("http").split(":")[1]);
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(TIME_OUT);// 请求超时时间
            conn.setReadTimeout((TIME_OUT * 10));// 读取超时时间
            conn.setRequestMethod(METHOD_GET);
            conn.setRequestProperty("Charset", charset);
            conn.setRequestProperty("Content-Encoding", charset);
            conn.setRequestProperty("accept", "*/*");
//              conn.setRequestProperty("User-Agent",
//                      "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");

            conn.setRequestProperty("User-Agent",
                    userAents[new Double(Math.random()*20).intValue()]);
            conn.connect();
            // System.out.println("====="+conn.getHeaderField("Content-Type"));
            int responseCode = conn.getResponseCode();
            if (responseCode == HTTP_OK) {
                inStream = conn.getInputStream();
                response = getResponse(inStream);
            } else {
                response = "{\"status\":false,\"message\":\"请求异常码[" + responseCode + "]\"}";
                // response = "返回码：" + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return response;
    }
}

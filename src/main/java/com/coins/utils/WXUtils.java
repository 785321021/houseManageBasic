package com.coins.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coins.entity.weixin.SNSUserInfo;
import com.coins.entity.weixin.WeixinOauth2Token;
/**
 * 
 * @author duyl
 *
 */
public class WXUtils {
	/**
	 * dyl 微信 微信公众号测试id secret
	 */
//	public static final String APPID = "wxedc9a96aed1fa4a8";
//	public static final String SECRET = "202698dbf3f71e9105dce8aeb63e81c2";
	
	public static final String APPID = "wx8ff39089ef60074f";
	public static final String SECRET = "8701de43ad6fe5b459997323a1c4d460";
	
	private static final Logger log = LoggerFactory.getLogger(WXUtils.class);

	public static void main(String[] args) {
//		String acessToken = getMenu("");
//		String acessToken = getAcessToken("","","");
//		String acessToken = getCode("");
//		String acessToken = getOpenId("");
//		String acessToken = refushToken("");
//		String acessToken = getUserInfo("","");
		getAccessTokenByIdAndSecret(APPID,SECRET);
//		System.out.println(acessToken);
	}
	/**
	 * 获取sdk_ticket
	 * 
	 * @param accessToken 
	 * @return String
	 */
	public static String getSdkTicketByAccessToken(String accessToken) {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token=%s&type=2";
		requestUrl = String.format(requestUrl,accessToken);
		// 获取网页授权凭证
		String charset ="ISO-8859-1";
		String returnStr="";
		JSONObject jsonObject = JSONObject.parseObject(HttpRequestHelper.httpGet(requestUrl, charset));
		if (null != jsonObject) {
			try {
				return jsonObject.getString("ticket");
			} catch (Exception e) {
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取sdkTicket失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return returnStr;
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId     公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return String
	 */
	public static String getAccessTokenByIdAndSecret(String appId, String appSecret) {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?"
				+ "grant_type=client_credential&appid=%s&secret=%s";
		requestUrl = String.format(requestUrl, appId, appSecret);
		// 获取网页授权凭证
		String charset ="ISO-8859-1";
		String returnStr="";
		JSONObject jsonObject = JSONObject.parseObject(HttpRequestHelper.httpGet(requestUrl, charset));
		if (null != jsonObject) {
			try {
				return jsonObject.getString("access_token");
			} catch (Exception e) {
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return returnStr;
	}

	/**
	 * 获取网页授权凭证(access_token,openId等数据)
	 * 
	 * @param appId     公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		requestUrl = String.format(requestUrl, appId, appSecret, code);
		// 获取网页授权凭证
		JSONObject jsonObject = JSONObject.parseObject(HttpRequestHelper.postJson(requestUrl, null));
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken 网页授权接口调用凭证
	 * @param openId      用户标识
	 * @return SNSUserInfo
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = JSONObject.parseObject(HttpRequestHelper.postJson(requestUrl, null));

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getIntValue("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				JSONArray jsonArray = jsonObject.getJSONArray("privilege");
				if (jsonArray != null && jsonArray.size() > 0) {
					snsUserInfo
							.setPrivilegeList(JSONObject.parseArray(JSONObject.toJSONString(jsonArray), String.class));
				}
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}

	/**
	 * 获取token
	 * 
	 * @param grant_type
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAcessToken(String grant_type, String appid, String secret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s";

		appid = "wxedc9a96aed1fa4a8";
		grant_type = "client_credential";
		secret = "202698dbf3f71e9105dce8aeb63e81c2";

		String rs = String.format(url, grant_type, appid, secret);
		return HttpRequestHelper.postJson(rs, null);
	}



	/**
	 * 获取 自定义菜单
	 * 
	 * @param token
	 * @return
	 */
	public static String getMenu(String token) {
		token = "22_r4kHPCBeInNpuBJFAyPp4oM0DogSliMXVmmk9Hp4eBet0SM7ponzNEmY6tCna1-c3z1-YG7XVu55FHB4L8UZEGrQbC-L3A32o8deIMnrYY9bRLoekxn-_gIKtM8zKL4yQTJhxpefbwpWkzXFAPLdAJAHWS";
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
		return HttpRequestHelper.postJson(url, null);
	}

	/**
	 * 
	 * @param 获取code
	 * @return
	 */
	public static String getCode(String token) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		String redirect_uri = "http://meiquan.137idea.com/mq_mobile/auth.php";
		String rs = String.format(url, APPID, redirect_uri);
		System.out.println(rs);
		return HttpRequestHelper.postJson(rs, null);
	}

	/**
	 * 
	 * @param 获取OpenId {"access_token":"22_zFSJVpFE-8JTTnXIrb-xoZM_pJVkBW1EMgoP_QG9m4y6BapXx0heoiHSAqhnxwfqWbFMWw_M8ZI7C0HEzB3K7Pkj7C-Na1p2YGlhPDrz0j8",
	 *                 "expires_in":7200,
	 *                 "refresh_token":"22_UOUdSqs0DG_79US-6xtpoLI1zAcwn9VxV8tNUf35LkbTbHvhSZwIGiD-tYFWx-Be3ARHPBo1fTyTU-wUIejZkMfwCAAysOSgt-BVouRsvFI",
	 *                 "openid":"oB3OT57WMET1pz6YU8sGGZrtjXY8",
	 *                 "scope":"snsapi_userinfo"}
	 * @return
	 */
	public static String getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		code = "011phWKf18EXPr0T0cLf1h4TKf1phWKX";
		String rs = String.format(url, APPID, SECRET, code);
		return HttpRequestHelper.postJson(rs, null);

	}

	/**
	 * 刷新token
	 * 
	 * @param refresh_token
	 * @return
	 */
	public static String refushToken(String refresh_token) {
		String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
		refresh_token = "22_UOUdSqs0DG_79US-6xtpoLI1zAcwn9VxV8tNUf35LkbTbHvhSZwIGiD-tYFWx-Be3ARHPBo1fTyTU-wUIejZkMfwCAAysOSgt-BVouRsvFI";
		String rs = String.format(url, APPID, refresh_token);
		return HttpRequestHelper.postJson(rs, null);
		// {"openid":"oB3OT57WMET1pz6YU8sGGZrtjXY8",
		// "access_token":"22_zFSJVpFE-8JTTnXIrb-xoZM_pJVkBW1EMgoP_QG9m4y6BapXx0heoiHSAqhnxwfqWbFMWw_M8ZI7C0HEzB3K7Pkj7C-Na1p2YGlhPDrz0j8",
		// "expires_in":7200,
		// "refresh_token":"22_UOUdSqs0DG_79US-6xtpoLI1zAcwn9VxV8tNUf35LkbTbHvhSZwIGiD-tYFWx-Be3ARHPBo1fTyTU-wUIejZkMfwCAAysOSgt-BVouRsvFI",
		// "scope":"snsapi_base,snsapi_userinfo,"}
	}

	/**
	 * 查询用户信息
	 * 
	 * @param token,openid
	 * @return
	 */
	public static String getUserInfo(String token, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
		token = "22_zFSJVpFE-8JTTnXIrb-xoZM_pJVkBW1EMgoP_QG9m4y6BapXx0heoiHSAqhnxwfqWbFMWw_M8ZI7C0HEzB3K7Pkj7C-Na1p2YGlhPDrz0j8";
		openId = "oB3OT57WMET1pz6YU8sGGZrtjXY8";
		String rs = String.format(url, token, openId);
//		String postJson = HttpRequestHelper.postJson(rs, null);
//		JSONObject parseObject = JSONObject.parseObject(postJson);
//		String headImgUrl = parseObject.getString("headimgurl");
//		
//		if(!Strings.isNullOrEmpty(headImgUrl)) {
//			headImgUrl = headImgUrl.replaceAll("/","");
//		}
		return HttpRequestHelper.postJson(rs, null);
		// {"openid":"oB3OT57WMET1pz6YU8sGGZrtjXY8","nickname":"面条","sex":1,"language":"zh_CN","city":"大兴","province":"北京","country":"中国","headimgurl":"http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/Q0j4TwGTfTLW2kf2ddpsD4iapKPHBQQQppWXcJzwz5W1SGAMeTGugt57GPLWK6tqeMdtEGR8OWSxicQxtIW9s5tA\/132","privilege":[]}
	}
}

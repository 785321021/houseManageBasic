package com.coins.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coins.entity.Admins;
import com.google.common.collect.Maps;

public class JwtUtil {
	static String TTP_SECRET = "afb82ec324d34a9dsdfadfaafb82ec324d34a9dsdafb82ec324d34a9dsdafb82ec324d34a9dsdafb82ec324d34a9dsdafb82ec324d34a9dsd";

	/**
	 * 生成token
	 * 
	 * @param ttlMillis
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static String createJWT(long ttlMillis, Admins user) throws Exception {
		return getToken(ttlMillis, user);
	}

	public static String getToken(long ttlMillis, Admins user) throws Exception {
		// 过期时间
		Date date = new Date(System.currentTimeMillis() + ttlMillis);
		// 私钥及加密算法
		Algorithm algorithm = Algorithm.HMAC256(TTP_SECRET);
		// 设置头信息
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		// 附带username，userid
		return com.auth0.jwt.JWT.create().withHeader(header).withClaim("useruuid", user.getId())
				.withClaim("username", user.getName()).withExpiresAt(date).sign(algorithm);
	}

	public static Map<String, Claim> parseToken(String token) throws Exception {
		if (null == token || "".equals(token))
			return Maps.newHashMap();
		Algorithm algorithm = Algorithm.HMAC256(TTP_SECRET);
		JWTVerifier require = com.auth0.jwt.JWT.require(algorithm).build();
		DecodedJWT jwt = require.verify(token);
		return jwt.getClaims();
	}

	public static Integer parseJWTgetId(String token) throws Exception {
		return parseToken(token).get("useruuid").asInt();
	}

	public static String parseJWTgetName(String token) throws Exception {
		return parseToken(token).get("username").asString();
	}
}
package com.lhh.modules.sys.shiro;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
/**
 * JWT 加密解密
 * 
 * @author miaof
 *
 */
public class JWTUtil {

	@Value("${jwt.apiKey.secret: 'abcde'}")
	private static String secret = "abcd";

	// Sample method to construct a JWT

	/**
	 * 生成的tocken较短
	 * @param id
	 * @return
	 */
	public static String createJWT(String id) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setSubject(id).signWith(signatureAlgorithm, signingKey);
		return builder.compact();
	}
	
	/**
	 * 生成JWT 加入的参数较多，生成tocken长度大
	 * @param id
	 * @param issuer  该JWT的签发者，是否使用是可选的；
	 * @param subject 该JWT所面向的用户，是否使用是可选的；
	 * @param ttlMillis
	 * @return
	 */
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// iss: 该JWT的签发者，是否使用是可选的；
		// sub: 该JWT所面向的用户，是否使用是可选的；
		// aud: 接收该JWT的一方，是否使用是可选的；
		// exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
		// iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
		// nbf (Not Before)：如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟；，是否使用是可选的；

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}


    /**
     * 解析JWT
     * @param jwt
     */
	public static void parseJWT(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}
	 /**
     * 解析JWT
     * @param jwt
     */
	public static Claims reParseJWT(String jwt) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
	}

	public static void main(String[] args) {

		// iss: 该JWT的签发者，是否使用是可选的；
		// sub: 该JWT所面向的用户，是否使用是可选的；
		// aud: 接收该JWT的一方，是否使用是可选的；
		// exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；
		// iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；
		// 其他还有：
		// nbf (Not Before)：如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟；，是否使用是可选的；

		String tockem = createJWT("admin");
		System.out.println(tockem);
		System.out.println(tockem.length());
		// TODO Auto-generated method stub
		parseJWT(tockem);
	}

}

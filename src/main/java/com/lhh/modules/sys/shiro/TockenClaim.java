package com.lhh.modules.sys.shiro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.PrematureJwtException;

/**
 * Tocken 返回对象 封装
 * 
 * @author miaofu
 *
 */
public class TockenClaim {
	private String id;

	private String audience;// aud: 接收该JWT的一方，是否使用是可选的；

	private String issuer; // iss: 该JWT的签发者，是否使用是可选的；

	private String subject; // sub: 该JWT所面向的用户，是否使用是可选的；

	private Long issuedAt;// iat(issued at): 在什么时候签发的(UNIX时间)，是否使用是可选的；

	private Long expiration;// exp(expires): 什么时候过期，这里是一个Unix时间戳，是否使用是可选的；

	private Long notBefore;// nbf (Not Before)：如果当前时间在nbf里的时间之前，则Token不被接受；一般都会留一些余地，比如几分钟；，是否使用是可选的；

	private TockenClaim() {

	}

	public TockenClaim(Claims claims) {
		this.id = claims.getId();
		this.audience = claims.getAudience();
		this.issuer = claims.getIssuer();
		this.subject = claims.getSubject();
		this.issuedAt = claims.getIssuedAt().getTime();
		this.expiration = claims.getExpiration().getTime();
		this.notBefore = claims.getNotBefore().getTime();
	}

	/**
	 * 校验
	 * 
	 * @param tocken
	 * @return
	 */
	public boolean verify(String tocken) {
		boolean re = false;
		Claims claims = JWTUtil.reParseJWT(tocken);
		return re;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Long issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public Long getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(Long notBefore) {
		this.notBefore = notBefore;
	}

}

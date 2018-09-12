package com.lhh.modules.sys.shiro;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

/**
 * sesssionId jwt生成器
 * 
 * @author miaofu
 *
 */
public class JWTSessionIdGenerator implements SessionIdGenerator{

	@Override
	public Serializable generateId(Session session) {
		// TODO Auto-generated method stub
    	String id = JWTUtil.createJWT(UUID.randomUUID().toString());
		return id;
	}
	 

}

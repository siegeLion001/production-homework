package com.lhh.modules.sys.shiro;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

public class AppWebSessionManager extends DefaultWebSessionManager {


    @Override
    public Serializable getSessionId(SessionKey key) {
        // 得到jsessionId
        HttpServletRequest request = (HttpServletRequest) WebUtils.getRequest(key);
        String TOCKEN = request.getHeader("TOCKEN");
        if (TOCKEN != null) {
            return TOCKEN;
        }
        return super.getSessionId(key);
    }

}

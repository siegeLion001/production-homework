package com.lhh.modules.sys.shiro;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    public MyFormAuthenticationFilter(String csReStr) {
        super();
        this.csReStr = csReStr;
    }
    private String csReStr;

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        boolean b = isGo(request);
        if (b) {
            go(response);
        } else {
            super.saveRequest(request);
            redirectToLogin(request, response);
        }

    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        boolean b = isGo(request);
        if (b) {
            go(response);
        } else {
            String loginUrl = super.getLoginUrl();
            WebUtils.issueRedirect(request, response, loginUrl);
        }
    }

    protected boolean isGo(ServletRequest request) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String TOCKEN = httpServletRequest.getHeader("TOCKEN");
        if (TOCKEN != null) {
            return true;
        } else {
            return false;
        }
    }

    protected void go(ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(csReStr);
    }
}

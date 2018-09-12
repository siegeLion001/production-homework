package com.lhh.modules.sys.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.lhh.common.utils.R;


/**
 * Shiro的配置文件
 *
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2017/9/27 22:02
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(SessionIdGenerator sessionIdGenerator,
                                         RedisShiroSessionDAO redisShiroSessionDAO, @Value("${renren.redis.open}") boolean redisOpen,
                                         @Value("${renren.shiro.redis}") boolean shiroRedis) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        AppWebSessionManager sessionManager = new AppWebSessionManager();
        // 设置session过期时间为1小时(单位：毫秒)，默认为30分钟
//        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setGlobalSessionTimeout(-1);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        // 如果开启redis缓存且renren.shiro.redis=true，则shiro session存到redis里
        if (redisOpen && shiroRedis) {
            redisShiroSessionDAO.setSessionIdGenerator(sessionIdGenerator);
            sessionManager.setSessionDAO(redisShiroSessionDAO);
        } else {

            // 默认使用EnterpriseCacheSessionDAO

            EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
            // 设置JwtsessionIdGenerator
            // dao.setSessionIdGenerator(sessionIdGenerator);
            sessionManager.setSessionDAO(dao);
        }

        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/");

        Map filters = new HashMap<>();
        R r = R.error(110, "登录超时");
        String json = JSON.toJSONString(r);

        filters.put("authc", new MyFormAuthenticationFilter(json));
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/statics/**", "anon");
        filterMap.put("/homework/**", "anon");
        filterMap.put("/barcode/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/banana/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/sys/config/reDate", "anon");
        filterMap.put("/21api/**", "anon");
        filterMap.put("/stu/**", "anon");
        filterMap.put("/sad/**", "anon");
        filterMap.put("/tch/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/file/**", "anon");
        filterMap.put("/cmbook/**", "anon");
        filterMap.put("/cmtextcontent/**", "anon");
        filterMap.put("/cmpublisher/**", "anon");
        filterMap.put("/interaction/**", "anon");
        filterMap.put("/**", "authc");
        filterMap.put("/ueditor/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 将JWTSessionIdGenerator交于spring容器管理
     *
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        SessionIdGenerator sessionIdGenerator = new JWTSessionIdGenerator();
        return sessionIdGenerator;
    }
}

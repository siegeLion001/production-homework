package com.lhh.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhh.common.utils.Constant;
import com.lhh.common.utils.SpringContextUtils;
import com.lhh.modules.property.entity.PropertyEntity;
import com.lhh.modules.property.service.PropertyService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 应用启动时调用的方法
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2018/3/24 14:18
 */
@Component
public class AppStartBean implements InitializingBean {
	private static CacheManager        ehcacheManager;
    
    private static Cache			   proCache;

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PropertyService propertyService;
	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		 logger.info("初始化系统属性...");
		 ehcacheManager = (CacheManager) SpringContextUtils.getBean("ehcacheManager");
		 proCache = ehcacheManager.getCache(Constant.EHCACHE_PRO_NAME);
	     if (proCache == null)
	     {
	            ehcacheManager.addCache(Constant.EHCACHE_PRO_NAME);
	            proCache = ehcacheManager.getCache(Constant.EHCACHE_PRO_NAME);
	     }
		// 将字典信息加入缓存
		List<PropertyEntity> listPE = propertyService.findAll();
		for(PropertyEntity pe:listPE) {
			if(proCache.get(pe.getType())==null) {
				Map<Integer,PropertyEntity> map = new HashMap<Integer,PropertyEntity>();
				map.put(pe.getId(), pe);
				proCache.put(new Element(pe.getType(), map));
			}else {
				Element element = proCache.get(pe.getType());
				Map<Integer,PropertyEntity> map = null;
				if(element.getObjectValue()==null) {
					map = new HashMap<Integer,PropertyEntity>();
				}else {
					map = (Map<Integer, PropertyEntity>) element.getObjectValue();
				}
				map.put(pe.getId(), pe);
				proCache.put(element);
			}
		}
	}

}

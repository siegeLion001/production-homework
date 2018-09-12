package com.lhh.common.utils;

import java.util.Map;
import com.lhh.modules.property.entity.PropertyEntity;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * ehcache管理工具类
 * 
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2018/3/24 14:18
 */
public class EhcacheUtil {
private static CacheManager ehcacheManager;
    
    private static Cache        proCache;//阅览室基本信息
    

    static
    {
        ehcacheManager = (CacheManager) SpringContextUtils.getBean("ehcacheManager");
        proCache = ehcacheManager.getCache(Constant.EHCACHE_PRO_NAME);
        
        if(proCache ==null){
            ehcacheManager.addCache(Constant.EHCACHE_PRO_NAME);
            proCache = ehcacheManager.getCache(Constant.EHCACHE_PRO_NAME);
        }
        
    }
    
    /**
     * 根据分类和编码获取名称
     * @param type 分类
     * @param code 名称
     * @return
     */
    public static String getProCacheName(Integer type,Integer id) {
    	PropertyEntity entity = getPropertyEntity(type,id);
    	return entity==null?null:entity.getName();
    }
    
    /**
     * 根据分类和编码获取名称
     * @param type 分类
     * @param code 名称
     * @return
     */
    public static String getProCacheCode(Integer type,Integer id) {
    	PropertyEntity entity = getPropertyEntity(type,id);
    	return entity==null?null:entity.getCode();
    }
    
    /**
     * 根据分类和编码获取全部属性
     * @param type 分类
     * @param code 名称
     * @return
     */
    public static PropertyEntity getPropertyEntity(Integer type,Integer id) {
    	if(proCache.get(type)==null) {
    		return null;
    	}
    	Element element = proCache.get(type);
    	Map<Integer, PropertyEntity> map = (Map<Integer, PropertyEntity>) element.getObjectValue();
    	return map.containsKey(id)==true?map.get(id):null;
    }
}

package com.lhh.modules.resourcecollect.service;

import com.lhh.modules.resourcecollect.entity.ResourceCollectEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源收藏表 用于表示当前用户收藏的外部资源
 *
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-08-09 15:42:12
 */
public interface ResourceCollectService {

    ResourceCollectEntity queryObject(Integer id);

    List<ResourceCollectEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ResourceCollectEntity resourceCollect);

    void update(ResourceCollectEntity resourceCollect);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<String> queryResourceIds(Map<String, Object> map);

    /**
     * 判断资源有没有被收集
     *
     * @return
     */
    Boolean collectByAuthIdAndResourceId(String authId, String resourceId, Integer resourceType);

}

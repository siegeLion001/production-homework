package com.lhh.modules.resourcecollect.service.impl;

import com.lhh.modules.resourcecollect.dao.ResourceCollectDao;
import com.lhh.modules.resourcecollect.entity.ResourceCollectEntity;
import com.lhh.modules.resourcecollect.service.ResourceCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("resourceCollectService")
public class ResourceCollectServiceImpl implements ResourceCollectService {
    @Autowired
    private ResourceCollectDao resourceCollectDao;

    @Override
    public ResourceCollectEntity queryObject(Integer id) {
        return resourceCollectDao.queryObject(id);
    }

    @Override
    public List<ResourceCollectEntity> queryList(Map<String, Object> map) {
        return resourceCollectDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return resourceCollectDao.queryTotal(map);
    }

    @Override
    public void save(ResourceCollectEntity resourceCollect) {
        resourceCollectDao.save(resourceCollect);
    }

    @Override
    public void update(ResourceCollectEntity resourceCollect) {
        resourceCollectDao.update(resourceCollect);
    }

    @Override
    public void delete(Integer id) {
        resourceCollectDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        resourceCollectDao.deleteBatch(ids);
    }

    @Override
    public List<String> queryResourceIds(Map<String, Object> map) {
        return resourceCollectDao.queryResourceIds(map);
    }

    @Override
    public Boolean collectByAuthIdAndResourceId(String authId, String resourceId, Integer resourceType) {
        Map qm = new HashMap();
        qm.put("authId", authId);
        qm.put("resourceId", resourceId);
        qm.put("resourceType", resourceType);
        int i = resourceCollectDao.queryTotal(qm);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

}

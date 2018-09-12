package com.lhh.modules.cmpublisher.service.impl;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmbook.entity.CmBookEntity;
import com.lhh.modules.cmpublisher.dao.CmPublisherDao;
import com.lhh.modules.cmpublisher.entity.CmPublisherEntity;
import com.lhh.modules.cmpublisher.service.CmPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("cmPublisherService")
public class CmPublisherServiceImpl implements CmPublisherService {
    @Autowired
    private CmPublisherDao cmPublisherDao;

    @Override
    public CmPublisherEntity queryObject(Integer id) {
        return cmPublisherDao.queryObject(id);
    }

    @Override
    public List<CmPublisherEntity> queryList(Map<String, Object> map) {
        return cmPublisherDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cmPublisherDao.queryTotal(map);
    }

    @Override
    public void save(CmPublisherEntity cmPublisher) {
        cmPublisherDao.save(cmPublisher);
    }

    @Override
    public void update(CmPublisherEntity cmPublisher) {
        cmPublisherDao.update(cmPublisher);
    }

    @Override
    public void delete(Integer id) {
        cmPublisherDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        cmPublisherDao.deleteBatch(ids);
    }

    @Override
    public List<Option> queryOptions(Map<String, Object> map) {
        return cmPublisherDao.queryOptions(map);
    }

    @Override
    public Map<Integer, String> getPublisherMap() {
        List<CmPublisherEntity> list = this.queryList(new HashMap());
        Map re = new HashMap();
        for (CmPublisherEntity entiy : list) {
            re.put(entiy.getId(), entiy.getName());
        }
        return re;
    }

}

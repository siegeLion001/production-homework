package com.lhh.modules.cmtype.service.impl;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmpublisher.entity.CmPublisherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhh.modules.cmtype.dao.CmTypeDao;
import com.lhh.modules.cmtype.entity.CmTypeEntity;
import com.lhh.modules.cmtype.service.CmTypeService;


@Service("cmTypeService")
public class CmTypeServiceImpl implements CmTypeService {
    @Autowired
    private CmTypeDao cmTypeDao;

    @Override
    public CmTypeEntity queryObject(Integer id) {
        return cmTypeDao.queryObject(id);
    }

    @Override
    public List<CmTypeEntity> queryList(Map<String, Object> map) {
        return cmTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cmTypeDao.queryTotal(map);
    }

    @Override
    public void save(CmTypeEntity cmType) {
        cmTypeDao.save(cmType);
    }

    @Override
    public void update(CmTypeEntity cmType) {
        cmTypeDao.update(cmType);
    }

    @Override
    public void delete(Integer id) {
        cmTypeDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        cmTypeDao.deleteBatch(ids);
    }

    @Override
    public List<Option> queryOptions(Map<String, Object> map) {
        return cmTypeDao.queryOptions(map);
    }

    @Override
    public Map<Integer, String> getTypeMap() {
        List<CmTypeEntity> list = this.queryList(new HashMap());
        Map re = new HashMap();
        for (CmTypeEntity entiy : list) {
            re.put(entiy.getId(), entiy.getName());
        }
        return re;
    }

}

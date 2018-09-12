package com.lhh.modules.cmbook.service.impl;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmbook.dao.CmBookDao;
import com.lhh.modules.cmbook.entity.CmBookEntity;
import com.lhh.modules.cmbook.service.CmBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("cmBookService")
public class CmBookServiceImpl implements CmBookService {
    @Autowired
    private CmBookDao cmBookDao;

    @Override
    public CmBookEntity queryObject(Integer id) {
        return cmBookDao.queryObject(id);
    }

    @Override
    public List<CmBookEntity> queryList(Map<String, Object> map) {
        return cmBookDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cmBookDao.queryTotal(map);
    }

    @Override
    public void save(CmBookEntity cmBook) {
        cmBookDao.save(cmBook);
    }

    @Override
    public void update(CmBookEntity cmBook) {
        cmBookDao.update(cmBook);
    }

    @Override
    public void delete(Integer id) {
        cmBookDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        cmBookDao.deleteBatch(ids);
    }

    @Override
    public List<Option> queryOptions(Map<String, Object> map) {
        return cmBookDao.queryOptions(map);
    }

    @Override
    public Map<Integer, String> getBookMap() {
        List<CmBookEntity> list = this.queryList(new HashMap());
        Map re = new HashMap();
        for (CmBookEntity entiy : list) {
            re.put(entiy.getId(), entiy.getName());
        }
        return re;
    }

}

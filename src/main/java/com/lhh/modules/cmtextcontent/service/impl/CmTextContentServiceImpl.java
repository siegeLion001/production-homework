package com.lhh.modules.cmtextcontent.service.impl;

import com.lhh.modules.cmtextcontent.dao.CmTextContentDao;
import com.lhh.modules.cmtextcontent.entity.CmTextContentEntity;
import com.lhh.modules.cmtextcontent.service.CmTextContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("cmTextContentService")
public class CmTextContentServiceImpl implements CmTextContentService {
    @Autowired
    private CmTextContentDao cmTextContentDao;

    @Override
    public CmTextContentEntity queryObject(Integer id) {
        return cmTextContentDao.queryObject(id);
    }

    @Override
    public List<CmTextContentEntity> queryList(Map<String, Object> map) {
        return cmTextContentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cmTextContentDao.queryTotal(map);
    }

    @Override
    public void save(CmTextContentEntity cmTextContent) {
        cmTextContentDao.save(cmTextContent);
    }

    @Override
    public void update(CmTextContentEntity cmTextContent) {
        cmTextContentDao.update(cmTextContent);
    }

    @Override
    public void delete(Integer id) {
        cmTextContentDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        cmTextContentDao.deleteBatch(ids);
    }

}

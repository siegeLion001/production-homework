package com.lhh.modules.usermaterial.service.impl;

import com.lhh.modules.usermaterial.dao.UserMaterialDao;
import com.lhh.modules.usermaterial.entity.UserMaterialEntity;
import com.lhh.modules.usermaterial.service.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userMaterialService")
public class UserMaterialServiceImpl implements UserMaterialService {
    @Autowired
    private UserMaterialDao userMaterialDao;

    @Override
    public UserMaterialEntity queryObject(Integer userMaterialId) {
        return userMaterialDao.queryObject(userMaterialId);
    }

    @Override
    public List<UserMaterialEntity> queryList(Map<String, Object> map) {
        return userMaterialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userMaterialDao.queryTotal(map);
    }

    @Override
    public void save(UserMaterialEntity userMaterial) {
        userMaterialDao.save(userMaterial);
    }

    @Override
    public void update(UserMaterialEntity userMaterial) {
        userMaterialDao.update(userMaterial);
    }

    @Override
    public void delete(Integer userMaterialId) {
        userMaterialDao.delete(userMaterialId);
    }

    @Override
    public void deleteBatch(Integer[] userMaterialIds) {
        userMaterialDao.deleteBatch(userMaterialIds);
    }

}

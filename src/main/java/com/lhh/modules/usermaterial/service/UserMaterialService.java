package com.lhh.modules.usermaterial.service;

import com.lhh.modules.usermaterial.entity.UserMaterialEntity;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-31 17:25:32
 */
public interface UserMaterialService {

    UserMaterialEntity queryObject(Integer userMaterialId);

    List<UserMaterialEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserMaterialEntity userMaterial);

    void update(UserMaterialEntity userMaterial);

    void delete(Integer userMaterialId);

    void deleteBatch(Integer[] userMaterialIds);
}

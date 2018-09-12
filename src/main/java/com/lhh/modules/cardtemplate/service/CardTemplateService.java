package com.lhh.modules.cardtemplate.service;

import com.lhh.modules.cardtemplate.entity.CardTemplateEntity;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-07-09 09:46:50
 */
public interface CardTemplateService {

    CardTemplateEntity queryObject(Integer id);

    List<CardTemplateEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(CardTemplateEntity cardTemplate);

    void update(CardTemplateEntity cardTemplate);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}

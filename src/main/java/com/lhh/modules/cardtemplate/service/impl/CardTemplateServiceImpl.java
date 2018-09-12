package com.lhh.modules.cardtemplate.service.impl;

import com.lhh.modules.cardtemplate.dao.CardTemplateDao;
import com.lhh.modules.cardtemplate.entity.CardTemplateEntity;
import com.lhh.modules.cardtemplate.service.CardTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("cardTemplateService")
public class CardTemplateServiceImpl implements CardTemplateService {
    @Autowired
    private CardTemplateDao cardTemplateDao;

    @Override
    public CardTemplateEntity queryObject(Integer id) {
        return cardTemplateDao.queryObject(id);
    }

    @Override
    public List<CardTemplateEntity> queryList(Map<String, Object> map) {
        return cardTemplateDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cardTemplateDao.queryTotal(map);
    }

    @Override
    public void save(CardTemplateEntity cardTemplate) {
        cardTemplateDao.save(cardTemplate);
    }

    @Override
    public void update(CardTemplateEntity cardTemplate) {
        cardTemplateDao.update(cardTemplate);
    }

    @Override
    public void delete(Integer id) {
        cardTemplateDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        cardTemplateDao.deleteBatch(ids);
    }

}

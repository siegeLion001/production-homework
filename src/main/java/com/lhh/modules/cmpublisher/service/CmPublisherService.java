package com.lhh.modules.cmpublisher.service;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmpublisher.entity.CmPublisherEntity;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmPublisherService {

    CmPublisherEntity queryObject(Integer id);

    List<CmPublisherEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(CmPublisherEntity cmPublisher);

    void update(CmPublisherEntity cmPublisher);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<Option> queryOptions(Map<String, Object> map);

    Map<Integer,String> getPublisherMap();
}

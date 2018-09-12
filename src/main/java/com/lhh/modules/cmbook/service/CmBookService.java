package com.lhh.modules.cmbook.service;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmbook.entity.CmBookEntity;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmBookService {

    CmBookEntity queryObject(Integer id);

    List<CmBookEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(CmBookEntity cmBook);

    void update(CmBookEntity cmBook);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<Option> queryOptions(Map<String, Object> map);

    Map<Integer,String> getBookMap();
}

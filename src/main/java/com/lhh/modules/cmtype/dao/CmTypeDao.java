package com.lhh.modules.cmtype.dao;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmtype.entity.CmTypeEntity;
import com.lhh.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmTypeDao extends BaseDao<CmTypeEntity> {
    List<Option> queryOptions(Map<String, Object> map);
}

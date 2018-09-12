package com.lhh.modules.cmtextcontent.dao;

import com.lhh.common.vo.Option;
import com.lhh.modules.cmtextcontent.entity.CmTextContentEntity;
import com.lhh.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-05-28 15:13:10
 */
public interface CmTextContentDao extends BaseDao<CmTextContentEntity> {
    List<Option> queryOptions(Map<String, Object> map);
}

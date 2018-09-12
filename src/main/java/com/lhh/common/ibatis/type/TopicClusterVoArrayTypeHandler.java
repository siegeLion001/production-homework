package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lhh.modules.topiccluster.domain.TopicClusterVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(value = {TopicClusterVo[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class TopicClusterVoArrayTypeHandler extends BaseTypeHandler<TopicClusterVo[]> {

    private TopicClusterVo[] strToArray(String str) {
        List<TopicClusterVo> list = JSONArray.parseArray(str, TopicClusterVo.class);
        return list.toArray(new TopicClusterVo[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TopicClusterVo[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public TopicClusterVo[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new TopicClusterVo[0];
        }
        return strToArray(str);
    }

    @Override
    public TopicClusterVo[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopicClusterVo[0];
        }
        return strToArray(str);
    }

    @Override
    public TopicClusterVo[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopicClusterVo[0];
        }
        return strToArray(str);
    }
}

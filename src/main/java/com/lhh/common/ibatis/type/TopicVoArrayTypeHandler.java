package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lhh.modules.topic.domain.TopicVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(value = {TopicVo[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class TopicVoArrayTypeHandler extends BaseTypeHandler<TopicVo[]> {

    private TopicVo[] strToArray(String str) {
        List<TopicVo> list = JSONArray.parseArray(str, TopicVo.class);
        return list.toArray(new TopicVo[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TopicVo[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public TopicVo[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new TopicVo[0];
        }
        return strToArray(str);
    }

    @Override
    public TopicVo[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopicVo[0];
        }
        return strToArray(str);
    }

    @Override
    public TopicVo[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopicVo[0];
        }
        return strToArray(str);
    }
}

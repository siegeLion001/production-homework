package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.lhh.modules.classtop.entity.TopContent;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {TopContent.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class TopContentTypeHandler extends BaseTypeHandler<TopContent> {

    private TopContent strToObj(String str) {
        return JSON.parseObject(str, TopContent.class);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TopContent parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public TopContent getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new TopContent();
        }
        return strToObj(str);
    }

    @Override
    public TopContent getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopContent();
        }
        return strToObj(str);
    }

    @Override
    public TopContent getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new TopContent();
        }
        return strToObj(str);
    }
}

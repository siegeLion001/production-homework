package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.lhh.modules.stuhomework.domain.Analysis;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {Analysis.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class AnalysisTypeHandler extends BaseTypeHandler<Analysis> {

    private Analysis strToObj(String str) {
        return JSON.parseObject(str, Analysis.class);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Analysis parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Analysis getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new Analysis();
        }
        return strToObj(str);
    }

    @Override
    public Analysis getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Analysis();
        }
        return strToObj(str);
    }

    @Override
    public Analysis getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Analysis();
        }
        return strToObj(str);
    }
}

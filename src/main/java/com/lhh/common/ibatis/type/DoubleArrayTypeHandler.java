package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(value = {Double[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class DoubleArrayTypeHandler extends BaseTypeHandler<Double[]> {

    private Double[] strToArray(String str) {
        List<Double> list = JSONArray.parseArray(str, Double.class);
        return list.toArray(new Double[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Double[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Double[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new Double[0];
        }
        return strToArray(str);
    }

    @Override
    public Double[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Double[0];
        }
        return strToArray(str);
    }

    @Override
    public Double[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Double[0];
        }
        return strToArray(str);
    }
}

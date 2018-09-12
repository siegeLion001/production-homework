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

@MappedTypes(value = {Integer[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class IntegerArrayTypeHandler extends BaseTypeHandler<Integer[]> {

    private Integer[] strToArray(String str) {
        List<Integer> list = JSONArray.parseArray(str, Integer.class);
        return list.toArray(new Integer[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Integer[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new Integer[0];
        }
        return strToArray(str);
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Integer[0];
        }
        return strToArray(str);
    }

    @Override
    public Integer[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Integer[0];
        }
        return strToArray(str);
    }
}

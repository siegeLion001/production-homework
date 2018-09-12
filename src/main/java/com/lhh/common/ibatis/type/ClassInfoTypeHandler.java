package com.lhh.common.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.alibaba.fastjson.JSON;
import com.lhh.modules.homework.domain.ClassInfo;

@MappedTypes(value = {ClassInfo.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class ClassInfoTypeHandler extends BaseTypeHandler<ClassInfo> {

    private ClassInfo strToArray(String str) {
        return JSON.parseObject(str, ClassInfo.class);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ClassInfo parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public ClassInfo getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new ClassInfo();
        }
        return strToArray(str);
    }

    @Override
    public ClassInfo getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new ClassInfo();
        }
        return strToArray(str);
    }

    @Override
    public ClassInfo getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new ClassInfo();
        }
        return strToArray(str);
    }
}

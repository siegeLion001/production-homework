package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lhh.modules.homework.domain.ClassInfo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(value = {ClassInfo[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class ClassInfoArrayTypeHandler extends BaseTypeHandler<ClassInfo[]> {

    private ClassInfo[] strToArray(String str) {
        List<ClassInfo> list = JSONArray.parseArray(str, ClassInfo.class);
        return list.toArray(new ClassInfo[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ClassInfo[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public ClassInfo[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new ClassInfo[0];
        }
        return strToArray(str);
    }

    @Override
    public ClassInfo[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new ClassInfo[0];
        }
        return strToArray(str);
    }

    @Override
    public ClassInfo[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new ClassInfo[0];
        }
        return strToArray(str);
    }
}

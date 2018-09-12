package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.lhh.modules.stuhomework.domain.Evaluate;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {Evaluate.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class EvaluateTypeHandler extends BaseTypeHandler<Evaluate> {

    private Evaluate strToObj(String str) {
        return JSON.parseObject(str, Evaluate.class);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Evaluate parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Evaluate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new Evaluate();
        }
        return strToObj(str);
    }

    @Override
    public Evaluate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Evaluate();
        }
        return strToObj(str);
    }

    @Override
    public Evaluate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new Evaluate();
        }
        return strToObj(str);
    }
}

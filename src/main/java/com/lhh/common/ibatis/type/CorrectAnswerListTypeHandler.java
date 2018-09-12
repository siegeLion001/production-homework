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
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CorrectAnswerListTypeHandler
 * Author: cuihp
 * Date: 2018/6/11
 * Description:
 */
@MappedTypes(value = {List.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class CorrectAnswerListTypeHandler extends BaseTypeHandler<List<String>> {
    private List<String> strToArray(String str) {
        List<String> list = JSONArray.parseArray(str, String.class);
        return list;
    }
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> objects, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(objects));
    }
    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        if ("".equals(str) || str == null) {
            return new ArrayList<String>();
        }
        return strToArray(str);
    }
    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        if ("".equals(str) || str == null) {
            return new ArrayList<String>();
        }
        return strToArray(str);
    }
    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        if ("".equals(str) || str == null) {
            return new ArrayList<String>();
        }
        return strToArray(str);
    }

}
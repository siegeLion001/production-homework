package com.lhh.common.ibatis.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lhh.modules.stutopic.entity.po.CorrectImg;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(value = {CorrectImg[].class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class CorrectImgArrayTypeHandler extends BaseTypeHandler<CorrectImg[]> {

    private CorrectImg[] strToArray(String str) {
        List<CorrectImg> list = JSONArray.parseArray(str, CorrectImg.class);
        return list.toArray(new CorrectImg[list.size()]);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CorrectImg[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public CorrectImg[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if ("".equals(str) || str == null) {
            return new CorrectImg[0];
        }
        return strToArray(str);
    }

    @Override
    public CorrectImg[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new CorrectImg[0];
        }
        return strToArray(str);
    }

    @Override
    public CorrectImg[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if ("".equals(str) || str == null) {
            return new CorrectImg[0];
        }
        return strToArray(str);
    }
}

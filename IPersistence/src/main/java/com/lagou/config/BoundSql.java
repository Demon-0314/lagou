package com.lagou.config;

import com.lagou.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BoundSql
 * @Description TODO
 * @Author demon
 * @Date 2020/11/23 0:21
 * @Version 1.0
 */
public class BoundSql {
    private String sqlText;// 解析过后的sql

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String parseSql, List<ParameterMapping> parameterMappings) {
        this.parameterMappingList = parameterMappings;
        this.sqlText = parseSql;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}

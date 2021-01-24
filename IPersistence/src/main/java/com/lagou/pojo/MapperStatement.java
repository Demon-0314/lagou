package com.lagou.pojo;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

/**
 * @ClassName MapperStatement
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 20:14
 * @Version 1.0
 */
public class MapperStatement {
    /**
     * id标识
     */
    private String id;
    /**
     * 返回值参数
     */
    private String resultType;
    /**
     * 参数值类型
     */
    private String paramterType;
    /**
     * sql语句
     */
    private String sql;
    /**
     * crud类型
     */
    private String crudType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getCrudType() {
        return crudType;
    }

    public void setCrudType(String crudType) {
        this.crudType = crudType;
    }
}

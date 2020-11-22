package com.lagou.sqlsession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName SqlSession
 * @Description TODO
 * @Author demon
 * @Date 2020/11/22 23:41
 * @Version 1.0
 */
public interface SqlSession {
    /**
     * 查询所有
     */
    public <E> List<E> selectList(String statementid, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

    /**
     * 根据条件查询单个
     */
    public <T> T selectOne(String statementid, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException;
}

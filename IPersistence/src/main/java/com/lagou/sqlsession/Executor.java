package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName Executor
 * @Description TODO
 * @Author demon
 * @Date 2020/11/23 0:03
 * @Version 1.0
 */
public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException;
}

package com.lagou.sqlsession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName SqlSession
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 23:41
 * @Version 1.0
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementid
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> selectList(String statementid, Object... params) throws Exception;

    /**
     * 根据条件查询单个
     *
     * @param statementid
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T selectOne(String statementid, Object... params) throws Exception;

    /**
     * 添加
     *
     * @param statementid
     * @param params
     * @return
     */
    public int addUser(String statementid, Object... params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException;

    /**
     * 更新
     *
     * @param statementid
     * @param params
     * @return
     */
    public int update(String statementid, Object... params) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException;

    /**
     * 删除
     *
     * @param statementid
     * @param params
     * @return
     */
    public int delete(String statementid, Object... params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException;


    /**
     * 为Dao接口生成代理实现类
     *
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass);
}

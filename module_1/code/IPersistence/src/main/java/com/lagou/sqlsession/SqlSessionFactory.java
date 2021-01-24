package com.lagou.sqlsession;

/**
 * @ClassName SqlSessionFactory
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 21:14
 * @Version 1.0
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}

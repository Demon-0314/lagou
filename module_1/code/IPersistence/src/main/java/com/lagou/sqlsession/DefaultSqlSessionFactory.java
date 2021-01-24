package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 23:37
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}

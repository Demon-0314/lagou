package com.lagou.sqlsession;

import com.lagou.config.XmlConfingBuilder;
import com.lagou.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description TODO
 * @Author demon
 * @Date 2020/11/22 21:12
 * @Version 1.0
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        // 第一：使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfingBuilder xmlConfingBuilder = new XmlConfingBuilder();
        Configuration configuration = xmlConfingBuilder.parseConfig(inputStream);
        // 第二：创建sqlSessionFactory对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}

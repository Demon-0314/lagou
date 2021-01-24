package com.lagou.config;

import com.lagou.io.Resources;
import com.lagou.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName XMLConfingBuilder
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 21:42
 * @Version 1.0
 */
public class XmlConfingBuilder {
    private Configuration configuration;

    public XmlConfingBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 该方法就是配置文件使用dom4j进行解析，封装成配置类
     * @param inputStream
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        // <configuration>
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);
        // mapper.xml解析：拿到路径--字节输入流--dom4j进行解析
        List<Element> maplist = rootElement.selectNodes("//mapper");
        for (Element element : maplist) {
            String resource = element.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsSteam(resource);
            XmlMapperBuilder xmlMapperBuilder =new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsSteam);
        }
        return configuration;
    }
}

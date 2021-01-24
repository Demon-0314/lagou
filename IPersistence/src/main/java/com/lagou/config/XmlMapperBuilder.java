package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName XmlMapperBuilder
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 23:21
 * @Version 1.0
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            setMapperStatementMap(namespace, element, "select");
        }
        List<Element> updateList = rootElement.selectNodes("//update");
        for (Element element : updateList) {
            setMapperStatementMap(namespace, element, "update");
        }
        List<Element> deleteList = rootElement.selectNodes("//delete");
        for (Element element : deleteList) {
            setMapperStatementMap(namespace, element, "delete");
        }
        List<Element> insertList = rootElement.selectNodes("//insert");
        for (Element element : insertList) {
            setMapperStatementMap(namespace, element, "insert");
        }
    }

    private void setMapperStatementMap(String namespace, Element element, String select) {
        String id = element.attributeValue("id");
        String resultType = element.attributeValue("resultType");
        String paramterType = element.attributeValue("paramterType");
        String sqlText = element.getTextTrim();
        MapperStatement mapperStatement = new MapperStatement();
        mapperStatement.setId(id);
        mapperStatement.setResultType(resultType);
        mapperStatement.setParamterType(paramterType);
        mapperStatement.setSql(sqlText);
        configuration.getMapperStatementMap().put(namespace + "." + id, mapperStatement);
    }
}

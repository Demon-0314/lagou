package com.lagou.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 20:20
 * @Version 1.0
 */
public class Configuration {
    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 初始化一个map对象装MappedStatement对象
     */
    Map<String, MapperStatement> mapperStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMappedStatementMap(Map<String, MapperStatement> mappedStatementMap) {
        this.mapperStatementMap = mappedStatementMap;
    }

}

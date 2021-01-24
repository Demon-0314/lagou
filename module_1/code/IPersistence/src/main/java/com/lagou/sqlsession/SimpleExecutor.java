package com.lagou.sqlsession;

import com.lagou.config.BoundSql;
import com.lagou.pojo.Configuration;
import com.lagou.pojo.MapperStatement;
import com.lagou.utils.GenericTokenParser;
import com.lagou.utils.ParameterMapping;
import com.lagou.utils.ParameterMappingTokenHandler;
import org.omg.CORBA.FieldNameHelper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName simpleExecutor
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/23 0:05
 * @Version 1.0
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException {
        PreparedStatement preparedStatement = getPreparedStatement(configuration, mapperStatement, params == null ? null : params[0]);
        // 5.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mapperStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        // 6.封装返回结果集
        while (resultSet.next()) {
            Object obj = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段值
                Object value = resultSet.getObject(columnName);
                // 判断是否有当前成员属性，若没有，则跳过
                if(!hasField(resultTypeClass,columnName)){
                    continue;
                }
                // 使用反射或者内省，根据数据表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(obj, value);
            }
            objects.add(obj);
        }
        return (List<E>) objects;
    }

    private boolean hasField(Class<?> resultTypeClass, String columnName) {
        Field[] declaredField = resultTypeClass.getDeclaredFields();
        for (Field field : declaredField) {
            if (columnName.equals(field.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int update(Configuration configuration, MapperStatement mapperStatement, Object... params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = getPreparedStatement(configuration, mapperStatement, params[0]);
        return preparedStatement.executeUpdate();
    }

    private PreparedStatement getPreparedStatement(Configuration configuration, MapperStatement mapperStatement, Object param) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // 1.注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        // 2.获取sql语句,转换sql语句
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        // 4.设置参数
        String paramterType = mapperStatement.getParamterType();
        Class<?> paramterTypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            // 反射
            Field declaredField = paramterTypeClass.getDeclaredField(content);
            // 暴力访问
            declaredField.setAccessible(true);
            Object obj = declaredField.get(param);
            preparedStatement.setObject(i + 1, obj);
        }
        return preparedStatement;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        Class<?> aClass = null;
        if (paramterType != null) {
            aClass = Class.forName(paramterType);
        }
        return aClass;
    }

    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替
     * 2.解析出#{}里面的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        // #{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}

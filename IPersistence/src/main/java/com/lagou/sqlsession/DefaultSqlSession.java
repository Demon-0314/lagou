package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DefaultSqlSession
 * @Description TODO
 * @Author demon
 * @Date 2020/11/22 23:42
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        // 将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<E> list = simpleExecutor.query(configuration, mappedStatement, params);
        return list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException {
        List<Object> objects = selectList(statementid, params);
        if(objects.size() == 1){
            return (T) objects.get(0);
        }else{
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成动态代理,并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 1.组装方法入参
                String methodName = method.getName(); // 方法名
                String ClassName = method.getDeclaringClass().getName(); // 权限类名
                String statementId = ClassName + "." + methodName;
                // 获取被调用返回值类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }
                return selectOne(statementId, args);
            }
        });
        return (T) proxyInstance;
    }
}

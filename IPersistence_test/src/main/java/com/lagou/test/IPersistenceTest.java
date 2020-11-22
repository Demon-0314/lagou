package com.lagou.test;

import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlsession.SqlSession;
import com.lagou.sqlsession.SqlSessionFactory;
import com.lagou.sqlsession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @ClassName IPersistenceTest
 * @Description TODO
 * @Author demon
 * @Date 2020/11/22 19:42
 * @Version 1.0
 */
public class IPersistenceTest {

    @Test
    public void test() throws DocumentException, PropertyVetoException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 调用
        User user = new User();
        user.setId(1L);
        user.setUsername("张飞");
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2.toString());
    }
}

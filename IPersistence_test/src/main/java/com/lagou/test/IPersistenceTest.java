package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlsession.DefaultSqlSession;
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
import java.util.List;

/**
 * @ClassName IPersistenceTest
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 19:42
 * @Version 1.0
 */
public class IPersistenceTest {

    @Test
    public void findAll() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用
        User user = new User();
        user.setId(1L);
        user.setUsername("黄忠");
        IUserDao userDao  = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findByCondition(user);
        System.out.println(user2.toString());
        System.out.println("===============查询所有用户=======================");
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1.toString());
        }
    }

    @Test
    public void add() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 新增User
        User user = new User();
        user.setId(3L);
        user.setUsername("张飞");
        IUserDao userDao  = sqlSession.getMapper(IUserDao.class);
        userDao.addUser(user);
        System.out.println("=================查询所有用户=====================");
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1.toString());
        }
    }

    @Test
    public void updateUser() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 更新User
        User user = new User();
        user.setId(3L);
        user.setUsername("张小飞");
        IUserDao userDao  = sqlSession.getMapper(IUserDao.class);
        userDao.updateUser(user);
        System.out.println("=================查询所有用户=====================");
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1.toString());
        }
    }

    @Test
    public void deleteUser() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 删除User
        User user = new User();
        user.setId(3L);
        IUserDao userDao  = sqlSession.getMapper(IUserDao.class);
        userDao.deleteUser(user);
        System.out.println("=================查询所有用户=====================");
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1.toString());
        }
    }


}

package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import javax.swing.tree.VariableHeightLayoutCache;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author demon
 * @Date 2020/11/25 1:47
 * @Version 1.0
 */
public class MybatisTest {
    /**
     * 查询用户
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> users = sqlSession.selectList("user.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();

    }

    /**
     * 新增用户
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("赵云");
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 更新用户
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("");
        sqlSession.update("user.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("user.deleteUser", 3);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test6() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("张飞");
        List<User> users = mapper.findByCondition(user1);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test7() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] arr = {1,2};
        List<User> users = mapper.findByIds(arr);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}

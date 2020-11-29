package com.lagou.test;


import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.pojo.Order;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author demon
 * @Date 2020/11/29 12:52
 * @Version 1.0
 */
public class MybatisTest {

    @Test
    public void test() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);

        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order.toString());
        }

    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        List<User> allUserAndRole = mapper.findAllUserAndRole();
        for (User user : allUserAndRole) {
            System.out.println(user.toString());
        }
    }

    private IUserMapper userMapper;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void test4() throws IOException {
        System.out.println("=============查询====================");
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("=============新增====================");
        User user = new User();
        user.setId(100);
        user.setUsername("王平");
        userMapper.addUser(user);
        System.out.println("=============查询====================");
        users = userMapper.selectUser();
        for (User user1 : users) {
            System.out.println(user1);
        }

        System.out.println("=============更新=================");
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("赵云");
        userMapper.updateUser(user1);
        System.out.println("=============查询====================");
        users = userMapper.selectUser();
        for (User user2 : users) {
            System.out.println(user2);
        }

        System.out.println("=============删除====================");
        userMapper.DeleteById(100);
        System.out.println("=============查询====================");
        users = userMapper.selectUser();
        for (User user3 : users) {
            System.out.println(user3);
        }
    }
}

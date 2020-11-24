package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author demon
 * @Date 2020/11/25 0:04
 * @Version 1.0
 */
public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public List<User> findAll() throws Exception;

    /**
     * 根据条件进行用户查询
     *
     * @param user
     * @return
     * @throws Exception
     */
    public User findByCondition(User user) throws Exception;
}

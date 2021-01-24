package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author 智弘
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

    /**
     * 新增用户
     *
     * @param user
     * @throws Exception
     */
    public void addUser(User user) throws Exception;

    /**
     * 更新用户
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户
     *
     * @param user
     */
    public void deleteUser(User user);
}

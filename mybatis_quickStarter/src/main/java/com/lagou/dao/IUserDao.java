package com.lagou.dao;

import com.lagou.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author demon
 * @Date 2020/11/27 0:19
 * @Version 1.0
 */
public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return
     * @throws IOException
     */
    public List<User> findAll() throws IOException;

    /**
     * 多条件组合查询：演示if
     * @param user
     * @return
     */
    public List<User> findByCondition(User user);

    /**
     * 根据主键查询
     * @param ids
     * @return
     */
    public List<User> findByIds(int[] ids);
}

package com.lagou.mapper;

import com.lagou.pojo.Order;
import com.lagou.pojo.Role;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName IUserMapper
 * @Description TODO
 * @Author demon
 * @Date 2020/11/29 13:45
 * @Version 1.0
 */
public interface IUserMapper {
    // 查询所有用户信息，同时查询出每个用户关联的订单信息
    public List<User> findAll();

    // 查询所有用户、同时查询每个用户关联的角色信息
    public List<User> findAllUserAndRole();

    // 添加用户
    @Insert("insert into user values(#{id},#{username})")
    public void addUser(User user);

    // 更新用户
    @Update("update user set username = #{username} where id = #{id}")
    public void updateUser(User user);

    // 查询用户
    @Select("select * from user")
    public List<User> selectUser();

    @Delete("delete from user where id = #{id}")
    public void DeleteById(Integer id);

}

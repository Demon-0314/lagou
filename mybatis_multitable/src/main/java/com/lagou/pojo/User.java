package com.lagou.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author demon
 * @Date 2020/11/29 12:14
 * @Version 1.0
 */
public class User {
    private Integer id;
    private String username;

    // 该用户所具有的订单信息
    private List<Order> orderList;

    // 代表当前用户具备哪些角色
    private List<Role> roleList = new ArrayList<Role>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderList=" + orderList +
                ", roleList=" + roleList +
                '}';
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

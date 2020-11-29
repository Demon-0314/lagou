package com.lagou.mapper;

import com.lagou.pojo.Order;

import java.util.List;

/**
 * @ClassName IOrderMapper
 * @Description TODO
 * @Author demon
 * @Date 2020/11/29 12:23
 * @Version 1.0
 */
public interface IOrderMapper {
    // 查询订单的同时还查询该订单所属的用户
    public List<Order> findOrderAndUser();
}

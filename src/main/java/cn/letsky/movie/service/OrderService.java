package cn.letsky.movie.service;

import cn.letsky.movie.entity.Order;

import java.util.List;

public interface OrderService {

    Order getOrder(Integer id);

    List<Order> getOrders(Integer userId);

    void pay(Order order);
}

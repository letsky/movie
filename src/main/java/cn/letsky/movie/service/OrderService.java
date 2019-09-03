package cn.letsky.movie.service;

import cn.letsky.movie.entity.Order;

public interface OrderService {

    Order getOrder(Integer id);

    void pay(Order order);
}

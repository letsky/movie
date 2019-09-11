package cn.letsky.movie.repository;

import cn.letsky.movie.constant.OrderStatus;
import cn.letsky.movie.entity.Order;
import cn.letsky.movie.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    //test insert
    @Test
    public void test1() {
        Order order = Order.builder()
                .sceneId(1)
                .userId(1)
                .bookedSeat("xx")
                .status(OrderStatus.UNPAID)
                .ticketNum(2)
                .totalPrice(20)
                .build();
        int result = repository.insert(order);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        Order order = repository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(order);
    }

    //test findAll
    @Test
    public void test3() {
        List<Order> list = repository.findAll();
        assertNotEquals(0, list.size());
    }

    //test update
    @Test
    public void test4() {
        Order order = repository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        order.setBookedSeat("aa");
        int result = repository.update(order);
        assertEquals(1, result);
    }

    //test delete
    @Test
    public void test5() {
        int result = repository.delete(1);
        assertEquals(1, result);
    }
}
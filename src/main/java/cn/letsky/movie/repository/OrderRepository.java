package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface OrderRepository {

    //order为mysql关键字，需要用``

    @Select("SELECT * FROM `order` WHERE id = #{id}")
    Optional<Order> findById(Integer id);

    @Select("SELECT * FROM `order`")
    List<Order> findAll();

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO `order` (user_id, scene_id, ticket_num, total_price, booked_seat, status, create_time) " +
            "VALUES (#{userId}, #{sceneId}, #{ticketNum}, #{totalPrice}, #{bookedSeat}, #{status}, #{createTime})")
    int insert(Order order);

    // xml实现
    int update(Order order);

    @Delete("DELETE FROM `order` WHERE id = #{id}")
    int delete(Integer id);
}

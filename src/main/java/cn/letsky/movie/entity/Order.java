package cn.letsky.movie.entity;

import cn.letsky.movie.constrant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 场次id
     */
    private Integer sceneId;

    /**
     * 票数
     */
    private Integer ticketNum;

    /**
     * 总价
     */
    private Integer totalPrice;

    /**
     * 预订的座位
     */
    private String bookedSeat;

    /**
     * 订单状态
     * {@link cn.letsky.movie.constrant.OrderStatus}
     */
    private String status = OrderStatus.UNPAID;

    /**
     * 订单创建时间
     */
    private Date createTime = new Date();
}

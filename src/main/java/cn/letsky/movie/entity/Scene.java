package cn.letsky.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 场次
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    private Integer id;

    private Integer movieId;

    private String movieName;

    private Integer price;

    /**
     * 每个场次的座位数，固定为166
     */
    private Integer seatNum = 166;

    /**
     * 场次时间
     */
    private String showtime;

    /**
     * 已预订的座位，多个座位号用分隔符分开
     */
    private String bookedSeat;
}

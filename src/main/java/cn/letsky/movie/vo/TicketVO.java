package cn.letsky.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketVO {

    private Integer id;

    private Date createTime;

    private Integer totalPrice;

    private String movieName;

    private String showtime;

    private String bookedSeat;
}

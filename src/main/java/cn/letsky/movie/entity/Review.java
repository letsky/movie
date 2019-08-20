package cn.letsky.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 影评
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Integer id;

    private String content;

    private Integer movieId;

    private Integer userId;

    private Date createTime;
}

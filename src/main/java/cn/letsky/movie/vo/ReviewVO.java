package cn.letsky.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {

    private Integer id;

    private String content;

    private Integer movieId;

    private Integer userId;

    private String nickname;

    private Date createTime;
}

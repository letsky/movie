package cn.letsky.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieVO {

    private Integer id;

    private String name;

    /**
     * 电影时长
     */
    private Integer duration;

    /**
     * 导演
     */
    private String directors;

    /**
     * 演员
     */
    private String actors;

    /**
     * 上映日期
     */
    private Date releaseDate;

    /**
     * 剧情
     */
    private String plot;

    /**
     * 电影海报
     */
    private String poster;

    /**
     * 国家
     */
    private String country;

    /**
     * 电影状态 上映/下映
     * {@link cn.letsky.movie.constrant.MovieStatus}
     */
    private Integer status;

    /**
     * 类别
     */
    private String categories;

    /**
     * 评分
     */
    private Double score;
}

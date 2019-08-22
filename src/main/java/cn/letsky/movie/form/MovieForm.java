package cn.letsky.movie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieForm {

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
     * 国家
     */
    private String country;

    /**
     * 类别
     */
    private Integer[] categoryIds;

    /**
     * 电影状态 上映/下映
     * {@link cn.letsky.movie.constrant.MovieStatus}
     */
    private String status;
}
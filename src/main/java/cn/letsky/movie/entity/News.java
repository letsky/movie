package cn.letsky.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 资讯
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private Integer id;

    private String content;

    private Date createTime = new Date();

    public News(String content) {
        this.content = content;
    }
}

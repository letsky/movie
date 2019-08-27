package cn.letsky.movie.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankVO {

    private Integer id;

    private Integer userId;

    private Integer movieId;

    private Double score;
}

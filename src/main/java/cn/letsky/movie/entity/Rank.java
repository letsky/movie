package cn.letsky.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rank {

    private Integer id;

    private Integer userId;

    private Integer movieId;

    private Integer score;
}

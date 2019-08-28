package cn.letsky.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {

    private Integer id;

    private Integer user_id;

    private Integer movie_id;
}

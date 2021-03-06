package cn.letsky.movie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankForm {

    private Integer userId;

    private Integer movieId;

    @Range(min = 0, max = 5)
    private Integer score;
}

package cn.letsky.movie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankForm {

    @NotBlank(message = "用户id不能为空")
    private Integer userId;

    @NotBlank(message = "电影id不能为空")
    private Integer movieId;

    @Range(min = 0, max = 5)
    private Integer score;
}

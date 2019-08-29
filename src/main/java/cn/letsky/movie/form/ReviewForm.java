package cn.letsky.movie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {

    private Integer userId;

    private Integer movieId;

    @NotBlank(message = "评论不能为空")
    private String content;
}

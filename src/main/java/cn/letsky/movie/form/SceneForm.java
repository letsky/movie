package cn.letsky.movie.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SceneForm {

    private Integer movieId;

    private Integer price;

    @NotBlank(message = "播放时间不能为空")
    private String showtime;
}

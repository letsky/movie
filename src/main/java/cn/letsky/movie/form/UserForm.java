package cn.letsky.movie.form;

import cn.letsky.movie.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private String nickname;

    @Email(message = "请输入正确的邮箱格式")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String phone;

    /**
     * 性别
     * {@link cn.letsky.movie.constant.Gender}
     */
    private Integer gender = Gender.OTHER;
}

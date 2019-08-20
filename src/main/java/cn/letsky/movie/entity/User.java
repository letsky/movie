package cn.letsky.movie.entity;

import cn.letsky.movie.constrant.Gender;
import cn.letsky.movie.constrant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String nickname;

    private String email;

    private String password;

    private String salt;

    private String phone;

    /**
     * 性别
     * {@link cn.letsky.movie.constrant.Gender}
     */
    private Integer gender = Gender.OTHER;

    /**
     * 角色
     * {@link cn.letsky.movie.constrant.Role}
     */
    private String role = Role.USER;
}
package cn.letsky.movie.repository;

import cn.letsky.movie.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    Optional<User> findById(Integer id);

    @Select("SELECT * FROM `user` WHERE email = #{email}")
    Optional<User> findByEmail(String email);

    @Select("SELECT * FROM `user`")
    List<User> findAll();

    @Insert("INSERT INTO `user` (nickname, email, password, salt, phone, gender, role) " +
            "VALUES (#{nickname}, #{email}, #{password}, #{salt}, #{phone}, #{gender}, #{role})")
    int insert(User user);

    // xml实现
    int update(User user);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    int delete(Integer id);
}

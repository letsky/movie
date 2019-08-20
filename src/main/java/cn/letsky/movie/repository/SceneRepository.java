package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Scene;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface SceneRepository {

    @Select("SELECT * FROM `scene` WHERE id = #{id}")
    Optional<Scene> findById(Integer id);

    @Select("SELECT * FROM `scene`")
    List<Scene> findAll();

    @Insert("INSERT INTO `scene` (movie_id, movie_name, price, seat_num, showtime, booked_seat)" +
            "VALUES (#{movieId}, #{movieName}, #{price}, #{seatNum}, #{showtime}, #{bookedSeat})")
    int insert(Scene scene);

    // xml实现
    int update(Scene scene);

    @Delete("DELETE FROM `scene` WHERE id = #{id}")
    int delete(Integer id);
}

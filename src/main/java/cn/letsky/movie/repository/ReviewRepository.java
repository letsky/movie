package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ReviewRepository {

    @Select("SELECT * FROM `review` WHERE id = #{id}")
    Optional<Review> findById(Integer id);

    @Select("SELECT * FROM `review`")
    List<Review> findAll();

    @Select("SELECT COUNT(*) FROM `review` WHERE movie_id = #{movieId}")
    Long count(Integer movieId);

    @Insert("INSERT into `review` (content, movie_id, user_id, create_time)" +
            "VALUES (#{content}, #{movieId}, #{userId}, #{createTime})")
    int insert(Review review);

    // xml实现
    int update(Review review);

    @Delete("DELETE FROM `review` WHERE id = #{id}")
    int delete(Integer id);

    @Delete("DELETE FROM `review` WHERE movie_id = #{movieId}")
    int deleteByMovieId(Integer movieId);
}

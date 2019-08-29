package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Review;
import cn.letsky.movie.vo.ReviewVO;
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

    @Select("SELECT r.*, u.nickname FROM `review` r LEFT JOIN user u " +
            "ON r.user_id = u.id WHERE movie_id = #{movieId} ORDER BY create_time DESC")
    List<ReviewVO> findAllByMovieId(Integer movieId);

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

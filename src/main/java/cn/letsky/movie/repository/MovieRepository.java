package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Category;
import cn.letsky.movie.entity.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface MovieRepository {

    @Select("SELECT * FROM `movie` WHERE id = #{id}")
    Optional<Movie> findById(Integer id);

    @Select("SELECT * FROM `movie`")
    List<Movie> findAll();

    @Insert("INSERT INTO `movie` (name, duration, directors, actors, " +
            "release_date, plot, poster, country, category_id, status) " +
            "VALUES (#{name}, #{duration}, #{directors}, #{actors}, #{releaseDate}, " +
            "#{plot}, #{poster}, #{country}, #{categoryId}, #{status})")
    int insert(Movie movie);

    // xml实现
    int update(Movie movie);

    @Delete("DELETE FROM `movie` WHERE id = #{id}")
    int delete(Integer id);
}

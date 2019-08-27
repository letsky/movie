package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface MovieRepository {

    // xml实现
    Optional<Movie> findById(Integer id);

    // xml实现
    List<Movie> findAllByCategoryId(Integer categoryId);

    // xml实现
    List<Movie> findAll();

    // xml实现
    List<Movie> findAllByStatus(Integer status);

    List<Movie> findLimitByStatus(@Param("status") Integer status, @Param("size") int size);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO `movie` (name, duration, directors, actors," +
            " release_date, plot, poster, country, status) " +
            "VALUES (#{name}, #{duration}, #{directors}, #{actors}, " +
            "#{releaseDate}, #{plot}, #{poster}, #{country}, #{status})")
    int insert(Movie movie);

    // xml实现
    int update(Movie movie);

    @Delete("DELETE FROM `movie` WHERE id = #{id}")
    int delete(Integer id);
}

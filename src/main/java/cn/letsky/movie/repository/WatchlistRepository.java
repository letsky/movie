package cn.letsky.movie.repository;

import cn.letsky.movie.entity.WatchList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface WatchlistRepository {

    @Select("SELECT * FROM `watchlist` WHERE id = #{id}")
    Optional<WatchList> findById(Integer id);

    @Select("SELECT * FROM `watchlist`")
    List<WatchList> findAll();

    @Insert("INSERT INTO `watchlist` (user_id, movie_id) VALUES (#{userId}, #{movieId})")
    int insert(WatchList watchList);

    int update(WatchList watchList);

    @Delete("DELETE FROM `watchlist` WHERE id = #{id}")
    int delete(Integer id);

    @Delete("DELETE FROM `watchlist` WHERE movie_id = #{movieId}")
    int deleteByMovieId(Integer movieId);
}

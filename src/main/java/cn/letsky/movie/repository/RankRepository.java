package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.vo.RankVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface RankRepository {

    @Select("SELECT * FROM `rank` WHERE id = #{id}")
    Optional<Rank> findById(Integer id);

    @Select("SELECT * FROM `rank`")
    List<Rank> findAll();

    /**
     * 评分最高的评分
     *
     * @return
     */
    @Select("SELECT movie_id, AVG(score) AS score, count(*) as num " +
            "FROM `rank` GROUP BY movie_id ORDER BY score DESC LIMIT 0, #{size}")
    List<RankVO> findLimitTop(Integer size);

    @Insert("INSERT INTO `rank` (user_id, movie_id, score) " +
            "VALUES (#{userId}, #{movieId}, #{score})")
    int insert(Rank rank);

    // xml
    int update(Rank rank);

    @Delete("DELETE FROM `rank` WHERE id = #{id}")
    int delete(Integer id);

    @Select("SELECT AVG(score) FROM `rank` WHERE movie_id = #{movieId}")
    Double findScoreByMovieId(Integer movieId);
}

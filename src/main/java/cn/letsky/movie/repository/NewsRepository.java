package cn.letsky.movie.repository;

import cn.letsky.movie.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface NewsRepository {

    @Select("SELECT * FROM `news` WHERE id = #{id}")
    Optional<News> findById(Integer id);

    @Select("SELECT * FROM `news`")
    List<News> findAll();

    @Select("SELECT * FROM `news` ORDER BY create_time DESC LIMIT 0, #{size}")
    List<News> findLimit(int size);

    @Insert("INSERT INTO `news` (title, content, create_time) VALUES (#{title}, #{content}, #{createTime})")
    int insert(News news);

    @Update("UPDATE `news` SET content = #{content}")
    int update(News news);

    @Delete("DELETE FROM `news` WHERE id = #{id}")
    int delete(Integer id);
}

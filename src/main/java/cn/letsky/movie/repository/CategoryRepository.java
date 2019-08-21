package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CategoryRepository {

    @Select("SELECT * FROM `category` WHERE id = #{id}")
    Optional<Category> findById(Integer id);

    @Select("SELECT * FROM `category`")
    List<Category> findAll();

    @Insert("INSERT INTO `category` (name) VALUES (#{name})")
    int insert(Category category);

    @Insert("INSERT INTO `movie_category_relationship` " +
            "(movie_id, category_id) VALUES (#{movieId}, #{categoryId})")
    int insertRelationship(@Param("movieId") int movieId, @Param("categoryId") int categoryId);

    @Update("UPDATE `category` SET name = #{name} WHERE id = #{id}")
    int update(Category category);

    @Delete("DELETE FROM `category` WHERE id = #{id}")
    int delete(Integer id);
}

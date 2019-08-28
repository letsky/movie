package cn.letsky.movie.service;

import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.vo.MovieVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MovieService {

    /**
     * 添加电影
     *
     * @param movie 电影实体
     * @param categoryIds 类别
     */
    void add(Movie movie, Integer[] categoryIds);

    /**
     * 更新电影
     * @param movie 电影实体
     * @param categoryIds 类别
     */
    void update(Movie movie, Integer[] categoryIds);

    /**
     * 删除电影
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取电影
     * @param id
     * @return
     */
    Movie getMovie(Integer id);

    /**
     * 获取全部电影，分页
     * @param page
     * @return
     */
    PageInfo<Movie> getMovies(Integer page, Integer size);

    /**
     * 获取全部电影，不分页
     * @return
     */
    List<Movie> getMovies();

    /**
     * 根据电影状态获取电影
     *
     * @param status
     * @param page
     * @param size
     * @return
     */
    PageInfo<Movie> getMoviesByStatus(Integer status, Integer page, Integer size);

    /**
     * 根据电影状态
     *
     * @param status
     * @return
     */
    List<Movie> getMoviesByStatus(Integer status);

    /**
     * 获取评分最高的电影，分页
     * @param page
     * @param size
     * @return
     */
    List<MovieVO> getTopMovies(Integer page, Integer size);

    /**
     * 根据类别获取电影
     * @param categoryId
     * @param page
     * @param size
     * @return
     */
    PageInfo<Movie> getMoviesByCategory(Integer categoryId, Integer page, Integer size);

    /**
     * 根据类别获取电影
     * @param categoryId
     * @return
     */
    List<Movie> getMoviesByCategory(Integer categoryId);

    /**
     * 检查电影是否存在，不存在抛出<code>EntityNotFoundException</code>异常
     *
     * @param movieId 电影id
     */
    Movie check(Integer movieId);
}

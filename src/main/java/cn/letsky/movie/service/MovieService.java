package cn.letsky.movie.service;

import cn.letsky.movie.entity.Movie;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MovieService {

    void add(Movie movie, Integer[] categoryIds);

    void update(Movie movie, Integer[] categoryIds);

    void delete(Integer id);

    Movie getMovie(Integer id);

    /**
     * 获取上映的电影
     *
     * @return
     */
    List<Movie> getReleasedMovie();

    List<Movie> getReleasedMovie(int size);

    PageInfo<Movie> getMovies(Integer page, Integer size);

    PageInfo<Movie> getMovies(Integer page);

    List<Movie> getTopMovies();

    PageInfo<Movie> getCategoryMovies(Integer categoryId, Integer page, Integer size);

    PageInfo<Movie> getCategoryMovies(Integer categoryId, Integer page);

    /**
     * 检查电影是否存在，不存在抛出<code>EntityNotFoundException</code>异常
     *
     * @param movieId 电影id
     */
    Movie check(Integer movieId);
}

package cn.letsky.movie.service;

import cn.letsky.movie.entity.Movie;

import java.util.List;

public interface MovieService {

    void add(Movie movie, Integer[] categoryIds);

    void update(Movie movie, Integer[] categoryIds);

    void delete(Integer id);

    /**
     * 获取上映的电影
     *
     * @return
     */
    List<Movie> getReleasedMovie();

    /**
     * 检查电影是否存在，不存在抛出<code>EntityNotFoundException</code>异常
     *
     * @param movieId 电影id
     */
    Movie check(Integer movieId);
}

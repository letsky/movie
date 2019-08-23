package cn.letsky.movie.service;

import cn.letsky.movie.entity.Movie;

public interface MovieService {

    void add(Movie movie, Integer[] categoryIds);

    void update(Movie movie, Integer[] categoryIds);

    void delete(Integer id);

    /**
     * 检查电影是否存在，不存在抛出<code>EntityNotFoundException</code>异常
     *
     * @param movieId 电影id
     */
    Movie check(Integer movieId);
}

package cn.letsky.movie.service;

import cn.letsky.movie.entity.Movie;

public interface MovieService {

    void add(Movie movie, Integer[] categoryIds);

    void update(Movie movie, Integer[] categoryIds);

    void delete(Integer id);
}

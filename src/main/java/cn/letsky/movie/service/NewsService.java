package cn.letsky.movie.service;

import cn.letsky.movie.entity.News;

import java.util.List;

public interface NewsService {

    News getNews(Integer id);

    List<News> getNews();

    List<News> getNews(int size);
}

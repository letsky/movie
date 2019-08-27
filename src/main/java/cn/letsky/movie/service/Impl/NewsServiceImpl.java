package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.News;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.NewsRepository;
import cn.letsky.movie.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getNews(Integer id) {
        return newsRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNews(int size) {
        return newsRepository.findLimit(3);
    }
}

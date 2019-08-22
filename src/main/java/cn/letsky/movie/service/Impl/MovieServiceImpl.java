package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.repository.MovieRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieServiceImpl(
            MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(Movie movie, Integer[] categoryIds) {
        int result = movieRepository.insert(movie);
        CommonUtils.checkInsert(result);
        int movieId = movie.getId();
        for (int categoryId : categoryIds) {
            int i = categoryRepository.insertRelationship(movieId, categoryId);
            CommonUtils.checkInsert(i);
        }
    }

    @Override
    public void update(Movie movie, Integer[] categoryIds) {

    }

    @Override
    public void delete(Integer id) {
        int result = movieRepository.delete(id);
        CommonUtils.checkDelete(result);
        int i = categoryRepository.deleteRelationship(id);
        CommonUtils.checkDelete(i);
    }
}

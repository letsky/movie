package cn.letsky.movie.service.Impl;

import cn.letsky.movie.constrant.MovieStatus;
import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.repository.MovieRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        int result = movieRepository.update(movie);
        CommonUtils.checkInsert(result);
        int movieId = movie.getId();
        int deleteResult = categoryRepository.deleteRelationship(movieId);
        CommonUtils.checkDelete(deleteResult);
        if (categoryIds != null) {
            for (int categoryId : categoryIds) {
                int i = categoryRepository.insertRelationship(movieId, categoryId);
                CommonUtils.checkInsert(i);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        int result = movieRepository.delete(id);
        CommonUtils.checkDelete(result);
        int i = categoryRepository.deleteRelationship(id);
        //TODO 删除场次
    }

    @Override
    public List<Movie> getReleasedMovie() {
        return movieRepository.findAllByStatus(MovieStatus.ON);
    }

    @Override
    public Movie check(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(EntityNotFoundException::new);
    }
}

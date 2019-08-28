package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.*;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.util.CommonUtils;
import cn.letsky.movie.vo.MovieVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final RankRepository rankRepository;
    private final ReviewRepository reviewRepository;
    private final SceneRepository sceneRepository;

    public MovieServiceImpl(
            MovieRepository movieRepository, CategoryRepository categoryRepository,
            RankRepository rankRepository, ReviewRepository reviewRepository,
            SceneRepository sceneRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.rankRepository = rankRepository;
        this.reviewRepository = reviewRepository;
        this.sceneRepository = sceneRepository;
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
        CommonUtils.checkDelete(i);
        int delete = reviewRepository.deleteByMovieId(i);
        CommonUtils.checkDelete(delete);
    }

    @Override
    public Movie getMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PageInfo<Movie> getMovies(Integer page, Integer size) {
        return PageHelper.startPage(page, size)
                .doSelectPageInfo(movieRepository::findAll);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public PageInfo<Movie> getMoviesByStatus(Integer status, Integer page, Integer size) {
        return PageHelper.startPage(page, size)
                .doSelectPageInfo(() -> movieRepository.findAllByStatus(status));
    }

    @Override
    public List<Movie> getMoviesByStatus(Integer status) {
        return movieRepository.findAllByStatus(status);
    }

    @Override
    public List<MovieVO> getTopMovies(Integer page, Integer size) {
        List<MovieVO> collect = rankRepository.findAllOrderByScore()
                .stream().map(e -> {
                    Integer movieId = e.getMovieId();
                    Movie movie = getMovie(movieId);
                    MovieVO movieVO = new MovieVO();
                    BeanUtils.copyProperties(movie, movieVO);
                    movieVO.setScore(e.getScore());
                    movieVO.setNum(e.getNum());
                    Long reviewNum = reviewRepository.count(movieId);
                    movieVO.setReviewNum(reviewNum);
                    return movieVO;
                }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageInfo<Movie> getMoviesByCategory(Integer categoryId, Integer page, Integer size) {
        return PageHelper.startPage(page, size)
                .doSelectPageInfo(() -> movieRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public List<Movie> getMoviesByCategory(Integer categoryId) {
        return movieRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Movie check(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(EntityNotFoundException::new);
    }
}

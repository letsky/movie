package cn.letsky.movie.service.Impl;

import cn.letsky.movie.constrant.MovieStatus;
import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.repository.MovieRepository;
import cn.letsky.movie.repository.RankRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.util.CommonUtils;
import cn.letsky.movie.vo.RankVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public MovieServiceImpl(
            MovieRepository movieRepository, CategoryRepository categoryRepository,
            RankRepository rankRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.rankRepository = rankRepository;
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
    public Movie getMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Movie> getReleasedMovie() {
        return movieRepository.findAllByStatus(MovieStatus.ON);
    }

    @Override
    public List<Movie> getReleasedMovie(int size) {
        return movieRepository.findLimitByStatus(MovieStatus.ON, size);
    }

    @Override
    public PageInfo<Movie> getMovies(Integer page, Integer size) {
        return PageHelper.startPage(page, size)
                .doSelectPageInfo(movieRepository::findAll);
    }

    @Override
    public PageInfo<Movie> getMovies(Integer page) {
        return getMovies(page, 20);
    }

    @Override
    public List<Movie> getTopMovies() {
        List<RankVO> allTop = rankRepository.findAllTop();
        List<Movie> collect = allTop.stream()
                .map(e -> movieRepository.findById(e.getMovieId())
                        .orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageInfo<Movie> getCategoryMovies(Integer categoryId, Integer page, Integer size) {
        return PageHelper.startPage(page, size)
                .doSelectPageInfo(() -> movieRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public PageInfo<Movie> getCategoryMovies(Integer categoryId, Integer page) {
        return getCategoryMovies(categoryId, page, 20);
    }

    @Override
    public Movie check(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(EntityNotFoundException::new);
    }
}

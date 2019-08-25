package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.form.MovieForm;
import cn.letsky.movie.repository.MovieRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.service.RankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(value = "*", allowCredentials = "true")
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final RankService rankService;

    public MovieController(
            MovieRepository movieRepository,
            MovieService movieService,
            RankService rankService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.rankService = rankService;
    }

    @GetMapping
    public ResponseEntity<PageInfo<Object>> getMovies(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageInfo<Object> pageInfo = PageHelper.startPage(page, size)
                .doSelectPageInfo(movieRepository::findAll);
        return ResponseEntity.ok(pageInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMovie(@PathVariable Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(movie);
    }

    @GetMapping(params = "categoryId")
    public ResponseEntity getMovieByCategory(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam("categoryId") Integer categoryId) {
        PageInfo<Object> pageInfo = PageHelper.startPage(page, size)
                .doSelectPageInfo(() -> movieRepository.findAllByCategoryId(categoryId));
        return ResponseEntity.ok(pageInfo);
    }

    @GetMapping("/released")
    public ResponseEntity getReleasedMovies() {
        List<Movie> releasedMovie = movieService.getReleasedMovie();
        return ResponseEntity.ok(releasedMovie);
    }

    @PostMapping
    public ResponseEntity addMovie(
            @RequestBody @Valid MovieForm movieForm, BindingResult bindingResult) {
        System.out.println(movieForm.getPoster());
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieForm, movie);
        movieService.add(movie, movieForm.getCategoryIds());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateMovie(
            @RequestBody @Valid MovieForm movieForm, BindingResult bindingResult) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieForm, movie);
        movieService.update(movie, movieForm.getCategoryIds());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable Integer id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{movieId}/ranks")
    public ResponseEntity getRankScore(@PathVariable Integer movieId) {
        //TODO 返回的数据格式有误
        Double avgScore = rankService.getAvg(movieId);
        return ResponseEntity.ok(avgScore);
    }
}

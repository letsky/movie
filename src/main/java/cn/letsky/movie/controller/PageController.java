package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Category;
import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.entity.News;
import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.service.NewsService;
import cn.letsky.movie.service.SceneService;
import cn.letsky.movie.vo.MovieVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * pages router
 */
@Controller
public class PageController {

    private final CategoryRepository categoryRepository;
    private final MovieService movieService;
    private final NewsService newsService;
    private final SceneService sceneService;

    public PageController(
            CategoryRepository categoryRepository, MovieService movieService,
            NewsService newsService, SceneService sceneService) {
        this.categoryRepository = categoryRepository;
        this.movieService = movieService;
        this.newsService = newsService;
        this.sceneService = sceneService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        //init top movies
        List<MovieVO> topMovies = movieService.getTopMovies(6);
        model.addAttribute("topMovies", topMovies);

        //init released movies
        List<Movie> releasedMovies = movieService.getReleasedMovie(8);
        model.addAttribute("releasedMovies", releasedMovies);

        //init 3 news
        List<News> news = newsService.getNews(3);
        model.addAttribute("news", news);
        return "index";
    }

    @GetMapping(value = "/movies")
    public String movies(
            @RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        //init categories
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        PageInfo<Movie> moviePageInfo = movieService.getMovies(page);
        model.addAttribute("movies", moviePageInfo);
        return "movie-list";
    }

    @GetMapping(value = "/movies", params = "categoryId")
    public String movies(
            @RequestParam(value = "categoryId") Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            Model model) {
        //init categories
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        //init movies by category
        PageInfo<Movie> moviePageInfo = movieService.getCategoryMovies(categoryId, page);
        model.addAttribute("movies", moviePageInfo);
        return "movie-list";
    }

    @GetMapping(value = "/movies", params = "status")
    public String getMoviesByStatus(
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            Model model) {
        //init categories
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        //init movies by category
        PageInfo<Movie> moviePageInfo = movieService.getMoviesByStatus(status, page);
        model.addAttribute("movies", moviePageInfo);
        return "movie-list";
    }

    @GetMapping(value = "/movie", params = "id")
    public String movie(@RequestParam("id") Integer id, Model model) {
        //init movie
        Movie movie = movieService.getMovie(id);
        model.addAttribute("movie", movie);

        //init scene
        List<Scene> scenes = sceneService.getScenes(id);
        model.addAttribute("scenes", scenes);

        //init comments
        return "movie";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "";
    }

    @GetMapping("/ranks")
    public String ranks(Model model) {
        List<MovieVO> topMovies = movieService.getTopMovies(10);
        model.addAttribute("top", topMovies);
        return "ranks";
    }

    @GetMapping(value = "/news")
    public String newsList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        PageInfo<Object> pageInfo = PageHelper.startPage(page, 20)
                .doSelectPageInfo(newsService::getNews);
        model.addAttribute("page", pageInfo);
        return "news-list";
    }

    @GetMapping(value = "/news", params = "id")
    public String news(@RequestParam("id") Integer id, Model model) {
        //init news
        News news = newsService.getNews(id);
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping(value = "/watchlist")
    public String watchlist(Model model) {
        return "watchlist";
    }

    @GetMapping("/ticket")
    public String ticket(Model model) {
        return "ticket";
    }
}

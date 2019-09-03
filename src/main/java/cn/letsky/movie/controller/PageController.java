package cn.letsky.movie.controller;

import cn.letsky.movie.constrant.MovieStatus;
import cn.letsky.movie.constrant.OrderStatus;
import cn.letsky.movie.entity.*;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.repository.ReviewRepository;
import cn.letsky.movie.service.*;
import cn.letsky.movie.vo.MovieVO;
import cn.letsky.movie.vo.RankVO;
import cn.letsky.movie.vo.ReviewVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * pages router
 */
@Controller
public class PageController {

    private final Integer DEFAULT_SIZE = 20;

    private final CategoryRepository categoryRepository;
    private final MovieService movieService;
    private final NewsService newsService;
    private final SceneService sceneService;
    private final RankService rankService;
    private final ReviewRepository reviewRepository;
    private final OrderService orderService;

    public PageController(
            CategoryRepository categoryRepository, MovieService movieService,
            NewsService newsService, SceneService sceneService,
            RankService rankService, ReviewRepository reviewRepository, OrderService orderService) {
        this.categoryRepository = categoryRepository;
        this.movieService = movieService;
        this.newsService = newsService;
        this.sceneService = sceneService;
        this.rankService = rankService;
        this.reviewRepository = reviewRepository;
        this.orderService = orderService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        //init 6 top movies
        List<MovieVO> topMovies = movieService.getTopMovies(1, 6);
        model.addAttribute("topMovies", topMovies);

        //init 8 released movies
        List<MovieVO> collect = movieService
                .getMoviesByStatus(MovieStatus.ON, 1, 8)
                .getList().stream().map(e -> transform(e))
                .collect(Collectors.toList());
        model.addAttribute("releasedMovies", collect);

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

        PageInfo<Movie> moviePageInfo = movieService.getMovies(page, DEFAULT_SIZE);
        PageInfo<MovieVO> newPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(moviePageInfo, newPageInfo);
        List<MovieVO> collect = moviePageInfo.getList()
                .stream().map(this::transform).collect(Collectors.toList());
        newPageInfo.setList(collect);
        model.addAttribute("movies", newPageInfo);
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
        PageInfo<Movie> moviePageInfo = movieService.getMoviesByCategory(categoryId, page, DEFAULT_SIZE);
        PageInfo<MovieVO> newPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(moviePageInfo, newPageInfo);
        List<MovieVO> collect = moviePageInfo.getList()
                .stream().map(this::transform).collect(Collectors.toList());
        newPageInfo.setList(collect);
        model.addAttribute("movies", newPageInfo);
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
        PageInfo<Movie> moviePageInfo = movieService.getMoviesByStatus(status, page, DEFAULT_SIZE);
        PageInfo<MovieVO> newPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(moviePageInfo, newPageInfo);
        List<MovieVO> collect = moviePageInfo.getList()
                .stream().map(this::transform).collect(Collectors.toList());
        newPageInfo.setList(collect);
        model.addAttribute("movies", newPageInfo);
        return "movie-list";
    }

    @GetMapping(value = "/movie", params = "id")
    public String movie(@RequestParam("id") Integer id, HttpSession session, Model model) {
        //init movie
        Movie movie = movieService.getMovie(id);
        MovieVO movieVO = transform(movie);
        model.addAttribute("movie", movieVO);

        model.addAttribute("myScore", 0);
        Object user = session.getAttribute("user");
        if (user != null) {
            Integer userId = (Integer) user;
            //init user score
            Integer myScore = rankService.getScore(userId, id);
            model.addAttribute("myScore", myScore);
        }

        //init scene
        List<Scene> scenes = sceneService.getScenes(id);
        model.addAttribute("scenes", scenes);

        //init comments
        List<ReviewVO> reviews = reviewRepository.findAllByMovieId(id);
        model.addAttribute("reviews", reviews);
        return "movie";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/ranks")
    public String ranks(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        List<MovieVO> topMovies = movieService.getTopMovies(page, DEFAULT_SIZE);
        model.addAttribute("top", topMovies);
        return "ranks";
    }

    @GetMapping("/news")
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

    @GetMapping(value = "/ticket", params = "id")
    public String ticket(@RequestParam("id") Integer id, Model model) {
        Order order = orderService.getOrder(id);
        Integer sceneId = order.getSceneId();
        Scene scene = sceneService.getScene(sceneId);
        String bookedSeats = order.getBookedSeat();
        model.addAttribute("order", order);
        model.addAttribute("scene", scene);
        model.addAttribute("bookedSeats", bookedSeats);
        return "final";
    }

    @GetMapping("/ticket")
    public String ticket(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return "error";
        }

//        Order order = orderService.getOrder(id);
//        Integer sceneId = order.getSceneId();
//        Scene scene = sceneService.getScene(sceneId);
//        String bookedSeats = order.getBookedSeat();
//        model.addAttribute("order", order);
//        model.addAttribute("scene", scene);
//        model.addAttribute("bookedSeats", bookedSeats);
        return "ticket";
    }

    @GetMapping(value = "/order", params = "sceneId")
    public String chooseScene(@RequestParam("sceneId") Integer sceneId, Model model) {
        Scene scene = sceneService.getScene(sceneId);
        model.addAttribute("scene", scene);
        return "orderSeat";
    }

    @GetMapping(value = "/order", params = {"sceneId", "sits"})
    public String order(
            @RequestParam("sceneId") Integer sceneId,
            @RequestParam("sits") String[] sits, Model model) {
        int num = sits.length;
        Scene scene = sceneService.getScene(sceneId);
        int price = scene.getPrice();
        int totalPrice = price * num;

        model.addAttribute("num", num);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("scene", scene);
        model.addAttribute("sits", sits);
        return "pay";
    }

    @PostMapping("/pay")
    public String pay(
            @RequestParam("userId") Integer userId, @RequestParam("sceneId") Integer sceneId,
            @RequestParam("sits") String[] sits, @RequestParam("phone") String phone) {
        Scene scene = sceneService.getScene(sceneId);
        int price = scene.getPrice();

        int num = sits.length;
        int totalPrice = num * price;
        String bookedSeats = Arrays.stream(sits).collect(Collectors.joining(","));
        Order order = Order.builder().sceneId(sceneId)
                .phone(phone)
                .status(OrderStatus.PAID)
                .ticketNum(num)
                .totalPrice(totalPrice)
                .userId(userId)
                .createTime(new Date())
                .bookedSeat(bookedSeats)
                .build();
        orderService.pay(order);
        return "redirect:ticket?id=" + order.getId();
    }

    private MovieVO transform(Movie movie) {
        Integer movieId = movie.getId();
        MovieVO movieVO = new MovieVO();
        BeanUtils.copyProperties(movie, movieVO);
        RankVO rankVO = rankService.getRankVO(movieId);
        movieVO.setNum(rankVO.getNum());
        movieVO.setScore(rankVO.getScore());
        Long reviewNum = reviewRepository.count(movieId);
        movieVO.setReviewNum(reviewNum);
        return movieVO;
    }
}

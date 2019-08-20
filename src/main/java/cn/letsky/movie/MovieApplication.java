package cn.letsky.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    //以下代码为测试页面
    @RequestMapping({"/index.html", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/login.html", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/register.html", "/register"})
    public String register() {
        return "register";
    }

    @RequestMapping({"/movie-list.html", "/movielist"})
    public String list() {
        return "movie-list";
    }

    @RequestMapping({"/rate.html", "/rate"})
    public String rate() {
        return "rate";
    }

    @RequestMapping({"/404.html", "/404"})
    public String error() {
        return "404";
    }

    @RequestMapping({"/news-list.html", "/newslist"})
    public String news() {
        return "news-list";
    }

    @RequestMapping({"/movie.html", "/movie"})
    public String movie() {
        return "movie";
    }

    @RequestMapping({"/book1.html", "/book1"})
    public String book1() {
        return "book1";
    }

    @RequestMapping({"/book2.html", "/book2"})
    public String book2() {
        return "book2";
    }

    @RequestMapping({"/book3.html", "/book3"})
    public String book3() {
        return "book3-buy";
    }

    @RequestMapping({"/book4.html", "/book4"})
    public String book4() {
        return "book-final";
    }

    @RequestMapping({"/watchlist.html", " /watchlist"})
    public String watchlist() {
        return "watchlist";
    }

    @RequestMapping({"/ticket.html", "/ticket"})
    public String ticket() {
        return "ticket";
    }
}

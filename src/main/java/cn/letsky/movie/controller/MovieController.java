package cn.letsky.movie.controller;

import cn.letsky.movie.form.MovieForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin(value = "*", allowCredentials = "true")
public class MovieController {

    @PostMapping
    public ResponseEntity addMovie(@RequestBody MovieForm movieForm) {
        System.out.println(movieForm);
        return null;
    }
}

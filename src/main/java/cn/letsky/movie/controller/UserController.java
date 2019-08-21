package cn.letsky.movie.controller;

import cn.letsky.movie.entity.User;
import cn.letsky.movie.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> login(@RequestBody User user) {
        check(user);
        userService.login(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        check(user);
        userService.register(user);
        return ResponseEntity.ok().build();
    }

    private void check(User user) {
    }
}

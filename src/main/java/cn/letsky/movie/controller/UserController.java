package cn.letsky.movie.controller;

import cn.letsky.movie.entity.User;
import cn.letsky.movie.form.UserForm;
import cn.letsky.movie.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> login(
            @RequestBody @Valid UserForm userForm, BindingResult bindingResult) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.login(user);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> register(
            @RequestBody @Valid UserForm userForm, BindingResult bindingResult) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.register(user);
        return ResponseEntity.ok().build();
    }
}

package cn.letsky.movie.controller.api;

import cn.letsky.movie.entity.User;
import cn.letsky.movie.form.UserForm;
import cn.letsky.movie.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(value = "*", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> login(
            @RequestBody @Valid UserForm userForm, HttpSession session, BindingResult bindingResult) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userService.login(user);
        User u = userService.getUserInfo(user.getEmail());
        session.setAttribute("user", u.getId());
        session.setAttribute("nickname", u.getNickname());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("nickname");
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

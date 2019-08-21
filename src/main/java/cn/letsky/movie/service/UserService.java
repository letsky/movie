package cn.letsky.movie.service;

import cn.letsky.movie.entity.User;

public interface UserService {

    void login(User user);

    void register(User user);

    User getUserInfo(Integer id);
}

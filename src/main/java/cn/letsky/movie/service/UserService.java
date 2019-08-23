package cn.letsky.movie.service;

import cn.letsky.movie.entity.User;

public interface UserService {

    void login(User user);

    void register(User user);

    User getUserInfo(Integer id);

    /**
     * 检查用户是否存在，不存在抛出<code>EntityNotFoundException</code>异常
     *
     * @param userId 用户id
     */
    User check(Integer userId);
}

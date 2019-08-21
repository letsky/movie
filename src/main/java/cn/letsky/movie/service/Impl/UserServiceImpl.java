package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.User;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.exception.GlobalException;
import cn.letsky.movie.repository.UserRepository;
import cn.letsky.movie.service.UserService;
import cn.letsky.movie.util.MD5Utils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void login(User user) {
        User storeUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        String storePassword = storeUser.getPassword();
        String encryptPassword = MD5Utils
                .encrypt(user.getPassword(), storeUser.getSalt());
        if (!storePassword.equals(encryptPassword)) {
            throw new GlobalException("账户名或密码错误!");
        }
    }

    @Override
    public void register(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(UserServiceImpl::registered);
        String salt = MD5Utils.getSalt();
        user.setSalt(salt);
        String encryptPassword = MD5Utils
                .encrypt(user.getPassword(), salt);
        user.setPassword(encryptPassword);
        int result = userRepository.insert(user);
        if (result < 1) {
            throw new GlobalException("注册失败");
        }
    }

    @Override
    public User getUserInfo(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    private static void registered(User user) {
        throw new GlobalException(user.getEmail() + "账号已经被注册了！");
    }
}

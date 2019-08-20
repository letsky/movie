package cn.letsky.movie.repository;

import cn.letsky.movie.entity.User;
import cn.letsky.movie.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    //test findById
    @Test
    public void test1() {
        User user = repository.findById(8)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(user);
    }

    //test findByEmail
    @Test
    public void test2() {
        User user = repository.findByEmail("111@qq.com")
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(user);
    }

    //test insert
    @Test
    public void test3() {
        User user = User.builder()
                .nickname("czq")
                .email("112@qq.com")
                .password("yyy")
                .phone("13722222222")
                .salt("xxxx")
                .build();
        int result = repository.insert(user);
        assertEquals(1, result);
    }

    //test update
    @Test
    public void test4() {
        User user = User.builder()
                .id(7)
                .nickname("xxx")
                .build();
        int result = repository.update(user);
        assertEquals(1, result);
    }

    //test delete
    @Test
    public void test5() {
        int result = repository.delete(13);
        assertEquals(1, result);
    }
}
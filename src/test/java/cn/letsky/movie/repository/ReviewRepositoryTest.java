package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Review;
import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

    //test insert
    @Test
    public void test1() {
        Review review = Review.builder()
                .content("xxx")
                .movieId(1)
                .userId(1)
                .createTime(new Date())
                .build();
        int result = repository.insert(review);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        Review review = repository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(review);
    }

    //test update
    @Test
    public void test3() {
        Review review = repository.findById(1).orElseThrow(EntityNotFoundException::new);
        review.setContent("xxxxaaaa");
        int result = repository.update(review);
        assertEquals(1, result);
    }

    //test findAll
    @Test
    public void test4() {
        List<Review> list = repository.findAll();
        assertNotEquals(0, list.size());
    }

    //test delete
    @Test
    public void test5() {
        int result = repository.delete(1);
        assertEquals(1, result);
    }
}
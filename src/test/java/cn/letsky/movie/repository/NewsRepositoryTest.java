package cn.letsky.movie.repository;

import cn.letsky.movie.entity.News;
import cn.letsky.movie.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    //test insert
    @Test
    public void test1() {
        News news = new News("content");
        int result = newsRepository.insert(news);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        News news = newsRepository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(news);
    }

    //test update
    @Test
    public void test3() {
        News news = newsRepository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        news.setContent("xxx");
        int result = newsRepository.update(news);
        assertEquals(1, result);
    }

    //test findAll
    @Test
    public void test4() {
        List<News> newsList = newsRepository.findAll();
        assertNotEquals(0, newsList.size());
    }

    //test delete
    @Test
    public void test5() {
        int result = newsRepository.delete(1);
        assertEquals(1, result);
    }
}
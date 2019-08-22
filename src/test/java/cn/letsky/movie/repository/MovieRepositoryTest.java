package cn.letsky.movie.repository;

import cn.letsky.movie.constrant.MovieStatus;
import cn.letsky.movie.entity.Movie;
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
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    //test insert
    @Test
    public void test1() {
        Movie movie = Movie.builder()
                .name("乘风破浪")
                .country("中国")
                .directors("韩寒")
                .actors("")
                .status(MovieStatus.ON)
                .build();
        int result = repository.insert(movie);
        System.out.println(movie);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        Movie movie = repository.findById(2)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println(movie);
        assertNotNull(movie);
    }

    //test update
    @Test
    public void test3() {
        Movie movie = repository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        movie.setActors("xxxxxxx");
        int result = repository.update(movie);
        assertEquals(1, result);
    }

    //test findAll
    @Test
    public void test4() {
        List<Movie> list = repository.findAll();
        System.out.println(list);
        assertNotEquals(0, list.size());
    }

    //test delete
    @Test
    public void test5() {
        int result = repository.delete(1);
        assertEquals(1, result);
    }

    //test findByCategoryId
    @Test
    public void test6() {
        List<Movie> list = repository.findAllByCategoryId(1);
        System.out.println(list);
        assertNotEquals(0, list.size());
    }
}
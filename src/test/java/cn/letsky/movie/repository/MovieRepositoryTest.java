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

    @Autowired
    private CategoryRepository categoryRepository;

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

    //test findAllByStatus
    @Test
    public void test7() {
        List<Movie> all = repository.findAllByStatus(MovieStatus.ON);
        System.out.println(all);
        System.out.println(all.size());
        assertNotEquals(0, all.size());
    }

    //insert many data
//    @Test
//    public void test8() {
//        Random random = new Random();
//        for (int i = 0; i < 100000; i++) {
//            Movie movie = Movie.builder()
//                    .name(RandomStringUtils.randomAlphabetic(10))
//                    .actors(RandomStringUtils.randomAlphabetic(4))
//                    .directors(RandomStringUtils.randomAlphabetic(3))
//                    .country(RandomStringUtils.randomAlphabetic(8))
//                    .duration(random.nextInt(150))
//                    .plot(RandomStringUtils.randomAlphabetic(80))
//                    .status((RandomUtils.nextBoolean() ? "on" : "off"))
//                    .build();
//            repository.insert(movie);
//            int movieId = movie.getId();
//            categoryRepository.insertRelationship(movieId, RandomUtils.nextInt(1, 4));
//        }
//    }

    @Test
    public void test9() {
        List<Movie> movies = repository.findLimitByStatus(MovieStatus.ON, 6);
        System.out.println(movies);
        assertNotEquals(0, movies.size());
    }
}
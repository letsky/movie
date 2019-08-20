package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Scene;
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
public class SceneRepositoryTest {

    @Autowired
    private SceneRepository repository;

    //test insert
    @Test
    public void test1() {
        Scene scene = Scene.builder()
                .movieId(1)
                .movieName("乘风破浪")
                .price(30)
                .showtime("12:10")
                .build();
        int result = repository.insert(scene);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        Scene scene = repository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(scene);
    }

    //test update
    @Test
    public void test3() {
        Scene scene = repository.findById(1).orElseThrow(EntityNotFoundException::new);
        scene.setPrice(40);
        int result = repository.update(scene);
        assertEquals(1, result);
    }

    //test findAll
    @Test
    public void test4() {
        List<Scene> list = repository.findAll();
        assertNotEquals(0, list.size());
    }

    //test delete
    @Test
    public void test5() {
        int result = repository.delete(1);
        assertEquals(1, result);
    }
}
package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Category;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    //test insert
    @Test
    public void test1() {
        Category category = new Category("xxx");
        int result = categoryRepository.insert(category);
        assertEquals(1, result);
    }

    //test findById
    @Test
    public void test2() {
        Category category = categoryRepository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(category);
    }

    //test findAll
    @Test
    public void test3() {
        List<Category> list = categoryRepository.findAll();
        assertNotEquals(0, list.size());
    }

    //test update
    @Test
    public void test4() {
        Category category = categoryRepository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        category.setName("sssss");
        int result = categoryRepository.update(category);
        assertEquals(1, result);
    }

    //test delete
    @Test
    public void test5() {
        int result = categoryRepository.delete(1);
        assertEquals(1, result);
    }

    @Test
    public void test6() {
        int result = categoryRepository.insertRelationship(3, 1);
        assertEquals(1, result);
    }
}
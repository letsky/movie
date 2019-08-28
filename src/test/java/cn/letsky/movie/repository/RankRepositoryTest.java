package cn.letsky.movie.repository;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.vo.RankVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankRepositoryTest {

    @Autowired
    private RankRepository rankRepository;

    @Test
    public void test1() {
        int result = rankRepository.insert(
                Rank.builder().userId(1).movieId(1).score(1).build()
        );
        assertEquals(1, result);
    }

    @Test
    public void test2() {
        Rank rank = rankRepository.findById(1)
                .orElseThrow(EntityNotFoundException::new);
        assertNotNull(rank);
    }

    @Test
    public void test3() {
        List<Rank> list = rankRepository.findAll();
        assertNotEquals(0, list.size());
    }

    @Test
    public void test4() {
        Rank rank = rankRepository.findById(1).orElseThrow(EntityNotFoundException::new);
        rank.setUserId(2);
        int result = rankRepository.update(rank);
        assertEquals(1, result);
    }

    @Test
    public void test5() {
        Double score = rankRepository.findScoreByMovieId(1);
        System.out.println(score);
        assertNotNull(score);
    }

    @Test
    public void test6() {
        int result = rankRepository.delete(1);
        assertEquals(1, result);
    }

    @Test
    public void test7() {
        List<RankVO> allTop = rankRepository.findLimitTop(6);
        System.out.println(allTop);
        assertNotEquals(0, allTop.size());
    }
}
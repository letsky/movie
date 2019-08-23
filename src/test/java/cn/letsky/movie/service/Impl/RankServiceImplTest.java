package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.service.RankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankServiceImplTest {

    @Autowired
    private RankService rankService;

    @Test
    public void add() {
        rankService.add(Rank.builder().movieId(2).userId(7).score(2).build());
    }
}
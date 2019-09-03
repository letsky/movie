package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.RankRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.service.RankService;
import cn.letsky.movie.service.UserService;
import cn.letsky.movie.util.CommonUtils;
import cn.letsky.movie.vo.RankVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;
    private final UserService userService;
    private final MovieService movieService;

    public RankServiceImpl(RankRepository rankRepository,
                           UserService userService,
                           MovieService movieService) {
        this.rankRepository = rankRepository;
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public void add(Rank rank) {
        int userId = rank.getUserId();
        int movieId = rank.getMovieId();
        userService.check(userId);
        movieService.check(movieId);
        Rank r = rankRepository.findByUserIdAndMovieId(userId, movieId).get();
        if (r != null) {
            Rank build = Rank.builder().id(r.getId()).score(rank.getScore()).build();
            rankRepository.update(build);
        } else {
            int result = rankRepository.insert(rank);
            CommonUtils.checkInsert(result);
        }
    }

    @Override
    public Integer getScore(Integer userId, Integer movieId) {
        Rank rank = rankRepository.findByUserIdAndMovieId(userId, movieId)
                .orElse(Rank.builder().score(0).build());
        return rank.getScore();
    }

    @Override
    public Double getAvgScore(Integer movieId) {
        movieService.check(movieId);
        return rankRepository.findScoreByMovieId(movieId);
    }

    @Override
    public RankVO getRankVO(Integer movieId) {
        return rankRepository.findByMovieId(movieId)
                .orElseThrow(EntityNotFoundException::new);
    }


}

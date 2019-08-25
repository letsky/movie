package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.repository.RankRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.service.RankService;
import cn.letsky.movie.service.UserService;
import cn.letsky.movie.util.CommonUtils;
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
        int result = rankRepository.insert(rank);
        CommonUtils.checkInsert(result);
    }

    @Override
    public Double getAvg(Integer movieId) {
        movieService.check(movieId);
        return rankRepository.findScoreByMovieId(movieId);
    }


}

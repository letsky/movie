package cn.letsky.movie.service;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.vo.RankVO;

public interface RankService {

    void add(Rank rank);

    Integer getScore(Integer userId, Integer movieId);

    Double getAvgScore(Integer movieId);

    RankVO getRankVO(Integer movieId);
}

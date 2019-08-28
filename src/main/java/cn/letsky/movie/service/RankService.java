package cn.letsky.movie.service;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.vo.RankVO;

public interface RankService {

    void add(Rank rank);

    Double getAvgScore(Integer movieId);

    RankVO getRankVO(Integer movieId);
}

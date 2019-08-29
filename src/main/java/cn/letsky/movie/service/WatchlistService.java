package cn.letsky.movie.service;

import cn.letsky.movie.entity.WatchList;

import java.util.List;

public interface WatchlistService {

    List<WatchList> getWatchlist();

    void add(WatchList watchList);

    /**
     * 判断电影是否在播放列表里
     *
     * @param userId
     * @param movieId
     * @return
     */
    boolean isExist(Integer userId, Integer movieId);
}

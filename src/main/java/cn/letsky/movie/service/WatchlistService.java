package cn.letsky.movie.service;

import cn.letsky.movie.entity.WatchList;

import java.util.List;

public interface WatchlistService {

    List<WatchList> getWatchlist();

    void add(WatchList watchList);
}

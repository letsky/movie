package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.WatchList;
import cn.letsky.movie.service.WatchlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImpl implements WatchlistService {
    @Override
    public List<WatchList> getWatchlist() {
        return null;
    }

    @Override
    public void add(WatchList watchList) {

    }

    @Override
    public boolean isExist(Integer userId, Integer movieId) {
        return false;
    }
}

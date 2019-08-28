package cn.letsky.movie.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @GetMapping
    public ResponseEntity getWatchlist() {
        return ResponseEntity.ok().build();
    }
}

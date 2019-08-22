package cn.letsky.movie.controller;

import cn.letsky.movie.entity.News;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.NewsRepository;
import cn.letsky.movie.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository repository;

    public NewsController(NewsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<PageInfo<Object>> getNews(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageInfo<Object> pageInfo = PageHelper.startPage(page, size)
                .doSelectPageInfo(repository::findAll);
        return ResponseEntity.ok(pageInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNew(@PathVariable Integer id) {
        News news = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity addNews(@RequestBody News news) {
        int result = repository.insert(news);
        CommonUtils.checkInsert(result);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNews(@PathVariable Integer id, @RequestBody News news) {
        isPresent(id);
        news.setId(id);
        int result = repository.update(news);
        CommonUtils.checkUpdate(result);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Integer id) {
        isPresent(id);
        int result = repository.delete(id);
        CommonUtils.checkDelete(result);
        return ResponseEntity.ok().build();
    }

    private News isPresent(Integer id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}

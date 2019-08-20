package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Category;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public PageInfo getCategories(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "size") Integer size) {
        return null;
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) {
        return null;
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return null;
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        return null;
    }
}

package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Category;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.CategoryRepository;
import cn.letsky.movie.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<PageInfo<Category>> getCategories(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageInfo<Category> pageInfo = PageHelper.startPage(page, size)
                .doSelectPageInfo(repository::findAll);
        return ResponseEntity.ok(pageInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
        Category category = isPresent(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        int result = repository.insert(category);
        CommonUtils.checkInsert(result);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable Integer id, @RequestBody Category category) {
        isPresent(id);
        category.setId(id);
        int result = repository.update(category);
        CommonUtils.checkUpdate(result);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        isPresent(id);
        int result = repository.delete(id);
        CommonUtils.checkDelete(result);
        return ResponseEntity.ok().build();
    }

    /**
     * 判断category是否存在
     *
     * @param id
     * @return
     */
    private Category isPresent(Integer id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}

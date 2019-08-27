package cn.letsky.movie.controller.api;

import cn.letsky.movie.entity.Movie;
import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.form.SceneForm;
import cn.letsky.movie.repository.SceneRepository;
import cn.letsky.movie.service.MovieService;
import cn.letsky.movie.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/scenes")
@CrossOrigin(value = "*", allowCredentials = "true")
public class SceneController {

    private final SceneRepository sceneRepository;
    private final MovieService movieService;

    public SceneController(SceneRepository sceneRepository, MovieService movieService) {
        this.sceneRepository = sceneRepository;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity getScenes(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PageInfo<Object> pageInfo = PageHelper.startPage(page, size)
                .doSelectPageInfo(sceneRepository::findAll);
        return ResponseEntity.ok(pageInfo);
    }

    @PostMapping
    public ResponseEntity addScene(@RequestBody @Valid SceneForm sceneForm, BindingResult bindingResult) {
        Movie movie = movieService.check(sceneForm.getMovieId());
        Scene scene = new Scene();
        scene.setMovieName(movie.getName());
        BeanUtils.copyProperties(sceneForm, scene);
        int result = sceneRepository.insert(scene);
        CommonUtils.checkInsert(result);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteScene(@PathVariable Integer id) {
        sceneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        int result = sceneRepository.delete(id);
        CommonUtils.checkDelete(result);
        return ResponseEntity.ok().build();
    }
}

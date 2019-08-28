package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.repository.SceneRepository;
import cn.letsky.movie.service.SceneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneServiceImpl implements SceneService {

    private final SceneRepository sceneRepository;

    public SceneServiceImpl(SceneRepository sceneRepository) {
        this.sceneRepository = sceneRepository;
    }

    @Override
    public List<Scene> getScenes(Integer movieId) {
        return sceneRepository.findByMovieId(movieId);
    }
}

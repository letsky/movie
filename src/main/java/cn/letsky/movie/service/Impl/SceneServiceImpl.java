package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.SceneRepository;
import cn.letsky.movie.service.SceneService;
import cn.letsky.movie.util.CommonUtils;
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

    @Override
    public Scene getScene(Integer sceneId) {
        return sceneRepository.findById(sceneId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void update(Scene scene) {
        int update = sceneRepository.update(scene);
        CommonUtils.checkUpdate(update);
    }
}

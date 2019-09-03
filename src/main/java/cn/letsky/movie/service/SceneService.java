package cn.letsky.movie.service;

import cn.letsky.movie.entity.Scene;

import java.util.List;

public interface SceneService {

    List<Scene> getScenes(Integer movieId);

    Scene getScene(Integer sceneId);

    void update(Scene scene);
}

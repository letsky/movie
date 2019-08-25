package cn.letsky.movie.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    String upload(MultipartFile multipartFile) throws IOException;
}

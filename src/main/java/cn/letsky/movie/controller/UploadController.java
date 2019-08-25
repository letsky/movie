package cn.letsky.movie.controller;

import cn.letsky.movie.exception.GlobalException;
import cn.letsky.movie.service.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new GlobalException("上传的文件为空");
        }
        String path = null;
        try {
            path = uploadService.upload(multipartFile);
        } catch (IOException e) {
            throw new GlobalException("上传异常");
        }
        return ResponseEntity.ok(path);
    }
}

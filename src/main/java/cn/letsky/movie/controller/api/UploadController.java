package cn.letsky.movie.controller.api;

import cn.letsky.movie.exception.GlobalException;
import cn.letsky.movie.service.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/upload")
@CrossOrigin(value = "*", allowCredentials = "true")
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
        try {
            String url = uploadService.upload(multipartFile);
            Map<String, String> map = new HashMap<>(1);
            map.put("url", url);
            return ResponseEntity.ok(map);
        } catch (IOException e) {
            throw new GlobalException("上传异常");
        }
    }
}

package cn.letsky.movie.service.Impl;

import cn.letsky.movie.exception.GlobalException;
import cn.letsky.movie.service.UploadService;
import cn.letsky.movie.util.ImageUtils;
import cn.letsky.movie.util.UploadProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    private final UploadProperties properties;

    public UploadServiceImpl(UploadProperties properties) {
        this.properties = properties;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        int position = originalFilename.lastIndexOf(".");
        if (position == -1) {
            throw new GlobalException("文件名不正确");
        }
        String suffix = originalFilename.substring(position);
        boolean isImage = ImageUtils.isImage(suffix);
        if (!isImage) {
            throw new GlobalException("非法的文件");
        }
        File file = new File(properties.getUploadPosition());
        if (!file.exists()) {
            file.createNewFile();
        }
        String filename = ImageUtils.generateFilename() + suffix;
        String path = properties.getUploadPosition() + File.separator + filename;
        multipartFile.transferTo(new File(path));
        return path;
    }

}

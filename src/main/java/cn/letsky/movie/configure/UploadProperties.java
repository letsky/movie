package cn.letsky.movie.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class UploadProperties {

    /**
     * 上传图片的位置
     */
    @Value("${upload.position}")
    private String uploadPosition;

    /**
     * 访问图片的前缀
     */
    @Value("${upload.suffix}")
    private String suffix;
}

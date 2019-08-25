package cn.letsky.movie.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class UploadProperties {

    @Value("${upload-position}")
    private String uploadPosition;
}

package cn.letsky.movie.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MovieConfiguration implements WebMvcConfigurer {

    private final UploadProperties uploadProperties;

    public MovieConfiguration(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadProperties.getSuffix() + "**")
                .addResourceLocations("file:" + uploadProperties.getUploadPosition() + File.separator);
    }
}

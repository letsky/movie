package cn.letsky.movie.configure;

import cn.letsky.movie.advice.PassportInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MovieConfiguration implements WebMvcConfigurer {

    private final UploadProperties uploadProperties;
    private final PassportInterceptor passportInterceptor;

    public MovieConfiguration(UploadProperties uploadProperties,
                              PassportInterceptor passportInterceptor) {
        this.uploadProperties = uploadProperties;
        this.passportInterceptor = passportInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor)
                .addPathPatterns("/ticket")
                .addPathPatterns("/watchlist");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadProperties.getSuffix() + "**")
                .addResourceLocations("file:" + uploadProperties.getUploadPosition() + File.separator);
    }
}

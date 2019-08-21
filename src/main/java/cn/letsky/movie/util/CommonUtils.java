package cn.letsky.movie.util;

import cn.letsky.movie.exception.GlobalException;
import org.springframework.http.ResponseEntity;

public class CommonUtils {

    public static ResponseEntity<Void> check(int result, String errorMessage) {
        if (result < 1) {
            throw new GlobalException(errorMessage);
        }
        return ResponseEntity.ok().build();
    }
}

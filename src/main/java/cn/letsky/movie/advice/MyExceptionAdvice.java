package cn.letsky.movie.advice;

import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 */
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity globalException(GlobalException ex) {
        return ResponseEntity.badRequest().build();
    }
}

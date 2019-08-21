package cn.letsky.movie.advice;

import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.exception.GlobalException;
import cn.letsky.movie.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> entityNotFoundException(EntityNotFoundException ex) {
        log.error(getTrace(ex));
        return new ResponseEntity<>(
                new Message("资源未找到"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Message> globalException(GlobalException ex) {
        log.error(getTrace(ex));
        return new ResponseEntity<>(
                new Message(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * 获取异常堆栈信息
     *
     * @param t throwable
     * @return
     */
    private String getTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        StringBuffer sb = sw.getBuffer();
        return sb.toString();
    }
}

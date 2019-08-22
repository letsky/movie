package cn.letsky.movie.advice;

import cn.letsky.movie.exception.GlobalException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class ValidatorAdvice {

    /**
     * aop处理校验信息
     *
     * @param bindingResult
     * @throws Throwable
     */
    @Before("execution(* cn.letsky.movie.controller..*.*(..)) && args(.., bindingResult)")
    public void before(BindingResult bindingResult) throws Throwable {
        if (bindingResult.hasErrors()) {
            throw new GlobalException(bindingResult.getFieldError().getDefaultMessage());
        }
    }
}

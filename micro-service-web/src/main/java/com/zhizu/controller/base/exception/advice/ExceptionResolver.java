package com.zhizu.controller.base.exception.advice;

import com.zhizu.common.business.BussinessException;
import com.zhizu.common.enums.ErrorMessage;
import com.zhizu.controller.base.dto.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * Created by zhengfeng on 2016-07-20 14:26.
 */
@ControllerAdvice
@Slf4j
public class ExceptionResolver {

    /**
     * 自定义异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BussinessException.class)
    public JsonResult customExceptionResolver(BussinessException e) {
        log.info("自定义异常出错 code: [{}], msg: [{}]", e.getCode(), e.getMsg());
        return JsonResult.error(e);
    }

    /**
     * 表单校验异常, 用于 bean validation 异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult validationExceptionResolver(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();

        log.info("MethodArgumentNotValidException: [{}]", error);

        return JsonResult.error(ErrorMessage.FORM_ERROR.getCode(),
                error.getObjectName() + " " + error.getField() + " " + error.getDefaultMessage(), null);
    }

    /**
     * 表单校验异常, 用于 form 参数绑定异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public JsonResult validationExceptionResolver(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();

        log.info("BindException: [{}]", error);

        return JsonResult.error(ErrorMessage.FORM_ERROR.getCode(),
                error.getObjectName() + " " + error.getField() + " " + error.getDefaultMessage(), null);

    }

    /**
     * 系统异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public JsonResult exceptionResolver(RuntimeException e) {
        log.warn("系统异常: [{}]", e);
        return JsonResult.error(ErrorMessage.SYSTEM_ERROR, e.getMessage());
    }
}

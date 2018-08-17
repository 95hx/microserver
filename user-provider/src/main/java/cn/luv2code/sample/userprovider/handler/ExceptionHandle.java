package cn.luv2code.sample.userprovider.handler;

import cn.luv2code.sample.userprovider.utils.Result;
import cn.luv2code.sample.userprovider.utils.ResultStatus;
import cn.luv2code.sample.userprovider.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandle {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler
    public Result handle(Exception e) {
        LOGGER.error(e.getMessage());
        return ResultUtils.error(ResultStatus.UNKNOWN_ERROR);
    }
}

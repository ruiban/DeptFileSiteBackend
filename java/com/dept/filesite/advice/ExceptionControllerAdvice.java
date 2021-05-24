package com.dept.filesite.advice;

import com.dept.filesite.entity.APIException;
import com.dept.filesite.entity.ResultCodeEnum;
import com.dept.filesite.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: ExceptionControllerAdvice
 * @description: 全局异常处理
 * @author: 201998
 * @create: 2020-09-28 15:45:00
 */

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(APIException.class)
    @ResponseBody
    public ResultVO<String> APIExceptionHandler(APIException e){
        return new ResultVO(ResultCodeEnum.FAILED,e.getMsg());
    }
}

package com.dept.filesite.advice;

import com.dept.filesite.entity.APIException;
import com.dept.filesite.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @className: ResponseControllerAdvice
 * @description: 全局处理响应结果
 * @author: 201998
 * @create: 2020-09-28 14:49:00
 */

@ControllerAdvice(basePackages = "com.dept.filesite.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //返回的结果不是ResultVO，进行处理
        //getGenericParameterTypes返回的是完整的泛型,getParameterTypes 只返回了类型，泛型没有返回
        //return !returnType.getGenericParameterType().equals(ResultVO.class);
        return !returnType.getParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResultVO(data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        return new ResultVO(data);
    }
}

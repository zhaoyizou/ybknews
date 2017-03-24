package com.visionet.core.controller;

import com.google.common.collect.Maps;
import com.visionet.core.exception.BizException;
import com.visionet.core.support.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


/**
 * @author liusy@visionet.com.cn
 * @ClassName: DefaultControllerExceptionHandler
 * @Description: TODO: 默认异常处理器
 * @date 2016年11月01日 上午11:38:19
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class DefaultControllerExceptionHandler {
    private static final Logger _logger = LoggerFactory.getLogger(DefaultControllerExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> processBaseAppRuntimeException(final Exception ex, final WebRequest req) {
        Map<String, String> map = Maps.newHashMap();
        if (ex instanceof BizException) {
            BizException exception = (BizException) ex;
            _logger.error("自定义异常：{}", ex.getMessage());
            String result = exception.getResult();
            Response response = Response.BUSSNESS_EXCEPTION();
            if (StringUtils.isBlank(result)){
                map.put("result", response.getResult());
            }else{
                map.put("result", result);
            }
            map.put("errmsg", ex.getMessage());
        } else {
            ex.printStackTrace();
            Response fail = Response.FAIL();
            map.put("result", fail.getResult());
            map.put("errmsg", fail.getErrmsg() +"，详细信息："+ ex.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}

package com.visionet.core.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.visionet.core.support.ResultType.*;

/**
 * author:liusy@visionet.com.cn
 * data:2016/11/7
 */
@ApiModel
public class Response<M> {

    @ApiModelProperty(value = "状态码 -1:根据业务返回相应的异常信息 0:成功 1:服务器异常 2:系统异常", required = true)
    private String result;
    @ApiModelProperty(value = "提示信息", required = true)
    private String errmsg;
    @ApiModelProperty(value = "返回内容", required = false)
    private M data;

    private Response(String result, String errmsg, M data) {
        this.result = result;
        this.errmsg = errmsg;
        this.data = data;
    }

    private static Builder builder(ResultType resultType) {
        return new DefaultBuilder(resultType.getResult(), resultType.getErrmsg());
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static Response SUCCESS() {
        Builder builder = builder(SUCCESS);
        return builder.build();
    }

    /**
     * 请求成功，包含返回新信息
     *
     * @return
     */
    public static <M> Response<M> SUCCESS(M body) {
        Builder builder = builder(SUCCESS);
        return builder.data(body);
    }

    /**
     * 请求成功，包含返回新信息
     *
     * @return
     */
    public static Response OK() {
        Builder builder = builder(SUCCESS);
        return builder.build();
    }

    /**
     * 请求成功，包含返回新信息
     *
     * @return
     */
    public static <M> Response<M> OK(M body) {
        Builder builder = builder(SUCCESS);
        return builder.data(body);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static Response FAIL() {
        Builder builder = builder(FAIL);
        return builder.build();
    }

    /**
     * 系统异常
     *
     * @return
     */
    public static Response SYSTEM_ERROR() {
        Builder builder = builder(SYSTEM_ERROR);
        return builder.build();
    }

    /**
     * 自定义异常
     *
     * @return
     */
    public static Response BUSSNESS_EXCEPTION() {
        Builder builder = builder(BUSSNESS_EXCEPTION);
        return builder.build();
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }

    /**
     * 创建异常参数接口
     */
    public interface Builder {
        Response<Void> build();

        <T> Response<T> data(T data);
    }

    /**
     * 创建异常参数默认实现
     */
    private static class DefaultBuilder implements Builder {
        private String result;
        private String errmsg;

        public DefaultBuilder(String result, String errmsg) {
            this.result = result;
            this.errmsg = errmsg;
        }

        @Override
        public Response<Void> build() {
            return data(null);
        }

        @Override
        public <T> Response<T> data(T data) {
            return new Response<>(result, errmsg, data);
        }
    }
}

package com.visionet.core.support;

/**
 * author:liusy@visionet.com.cn
 * data:2016/11/7
 */
public enum ResultType {
    SUCCESS("0", ""),
    FAIL("1", "服务器异常"),
    SYSTEM_ERROR("2", "渠道帐号失效"),
    BUSSNESS_EXCEPTION("-1", "业务异常"),
    SIGN_FAIL("-1", "校验签名失败");

    private String result;
    private String errmsg;

    ResultType(String result, String errmsg) {
        this.result = result;
        this.errmsg = errmsg;
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
}

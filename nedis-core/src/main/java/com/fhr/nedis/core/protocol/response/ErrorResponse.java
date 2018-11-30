package com.fhr.nedis.core.protocol.response;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class ErrorResponse extends RedisResponse {
    /**
     * 错误类型
     */
    private final String errType;
    /**
     * 错误信息
     */
    private final String errMsg;

    public ErrorResponse(String errType, String errMsg) {
        this.errType = errType;
        this.errMsg = errMsg;
    }

    public String getErrType() {
        return errType;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errType='" + errType + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

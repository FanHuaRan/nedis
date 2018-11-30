package com.fhr.nedis.core.protocol.response;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description 状态回复是一段以 "+" 开始、 "\r\n" 结尾的单行字符串。
 */
public class StatusResponse extends RedisResponse {
    private final String message;

    public StatusResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

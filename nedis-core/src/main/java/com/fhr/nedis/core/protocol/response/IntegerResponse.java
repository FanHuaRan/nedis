package com.fhr.nedis.core.protocol.response;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class IntegerResponse extends RedisResponse {
    private final Long value;

    public IntegerResponse(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntegerResponse{" +
                "value=" + value +
                '}';
    }
}

package com.fhr.nedis.core.protocol.response;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class IntegerResponse extends RedisResponse {
    private final int value;

    public IntegerResponse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntegerResponse{" +
                "value=" + value +
                '}';
    }
}

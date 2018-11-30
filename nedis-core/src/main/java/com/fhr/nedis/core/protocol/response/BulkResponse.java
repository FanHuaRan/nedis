package com.fhr.nedis.core.protocol.response;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class BulkResponse extends RedisResponse {
    private final String value;

    public BulkResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BulkResponse{" +
                "value='" + value + '\'' +
                '}';
    }
}

package com.fhr.nedis.core.protocol.request;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class RedisRequest {
    private final List<Object> params = new LinkedList<>();

    public List<Object> getParams() {
        return params;
    }

    private void addParam(String param){
        params.add(param);
    }

    public void addParam(int param){
        addParam(String.valueOf(param));
    }

    @Override
    public String toString() {
        return "RedisRequest{" +
                "params=" + params +
                '}';
    }
}

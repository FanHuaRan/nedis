package com.fhr.nedis.core.protocol.request;

import com.fhr.nedis.core.InvokeHandler;
import io.netty.util.concurrent.Promise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class RedisRequest {
    private final List<Object> params = new LinkedList<>();

    private Promise<Object> promise;

    public List<Object> getParams() {
        return params;
    }

    private void addParam(String param) {
        params.add(param);
    }

    public void addParam(Object param) {
        addParam(String.valueOf(param));
    }

    public void addManyParam(Object... params) {
        this.params.addAll(Arrays.asList(params)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    public Promise<Object> getPromise() {
        return promise;
    }

    public void setPromise(Promise<Object> promise) {
        this.promise = promise;
    }

    @Override
    public String toString() {
        return "RedisRequest{" +
                "params=" + params +
                ", promise=" + promise +
                '}';
    }
}

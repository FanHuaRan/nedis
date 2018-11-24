package com.fhr.nedis.core;

import java.util.concurrent.Future;

/**
 * Created by Huaran Fan on 2018/11/24
 *
 * @description Redis Client interface.
 */
public interface RedisClient {
    Future<String> get(String key);

    void getAsnc(String key, InvokeHandler handler);
}

package com.fhr.nedis.core;

import com.fhr.nedis.core.command.RedisCommand;

import java.util.concurrent.Future;

/**
 * Created by Huaran Fan on 2018/11/24
 *
 * @description Redis Client interface.
 */
public interface RedisClient {
    Future<Object> execCommand(RedisCommand command, Object... params);

    void execCommand(RedisCommand command, InvokeHandler invokeHandler, Object... params);
}

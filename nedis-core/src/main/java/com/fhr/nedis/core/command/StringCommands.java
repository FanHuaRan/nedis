package com.fhr.nedis.core.command;

import com.fhr.nedis.core.InvokeHandler;

import java.util.concurrent.Future;

/**
 * Created by Huaran Fan on 2018/11/24
 *
 * @description
 */
public interface StringCommands {
    Future<String> get(String key);

    void get(String key, InvokeHandler handler);

    Future<Integer> set(String key, String value);

    void set(String key, String value, InvokeHandler invokeHandler);
}

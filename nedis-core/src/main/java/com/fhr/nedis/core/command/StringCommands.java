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

    void getAsnc(String key, InvokeHandler handler);
}

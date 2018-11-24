package com.fhr.nedis.core;

/**
 * Created by Huaran Fan on 2018/11/24
 *
 * @description
 */
@FunctionalInterface
public interface InvokeHandler {
    void handler(Throwable throwable, Object resp);
}

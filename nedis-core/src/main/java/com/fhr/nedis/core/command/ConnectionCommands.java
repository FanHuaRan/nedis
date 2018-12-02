package com.fhr.nedis.core.command;

import com.fhr.nedis.core.InvokeHandler;

import java.util.concurrent.Future;

/**
 * Created by Huaran Fan on 2018/11/24
 *
 * @description 连接命令，包括：auth、echo、ping、quit、select
 */
public interface ConnectionCommands {
    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    Future<String> auth(String password);

    void auth(String password, InvokeHandler handler);

    /**
     * echo命令
     *
     * @param message
     * @return
     */
    Future<String> echo(String message);

    void echo(String message, InvokeHandler handler);

    /**
     * ping & return pong
     *
     * @return
     */
    Future<String> ping();

    void ping(InvokeHandler invokeHandler);

    /**
     * 退出redis连接
     *
     * @return
     */
    Future<String> quit();

    void quit(InvokeHandler invokeHandler);

    /**
     * 选择数据库
     *
     * @param dbNumber
     * @return
     */
    Future<String> select(int dbNumber);

    void select(int dbNumber, InvokeHandler invokeHandler);
}

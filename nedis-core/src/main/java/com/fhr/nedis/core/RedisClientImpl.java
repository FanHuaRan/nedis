package com.fhr.nedis.core;

import com.fhr.nedis.core.command.ConnectionCommands;
import com.fhr.nedis.core.command.RedisCommand;
import com.fhr.nedis.core.protocol.request.RedisRequest;
import com.fhr.nedis.core.protocol.response.RedisResponse;
import com.fhr.nedis.core.protocol.response.StatusResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Huaran Fan on 2018/12/1
 *
 * @description
 */
public class RedisClientImpl implements RedisClient, ConnectionCommands {
    private final Channel channel;

    public RedisClientImpl(Channel channel) {
        this.channel = channel;
    }

    @Override
    public <T> Future<T> execCommand(RedisCommand command, Object... params) {
        // 封装参数
        RedisRequest redisRequest = new RedisRequest();
        redisRequest.addParam(command);
        redisRequest.addManyParam(params);
        // eventLoop上实例化一个Promise，用于后面接受回复
        Promise<T> promise = channel.eventLoop().newPromise();
        // 发送消息
        channel.writeAndFlush(redisRequest);
        return promise;
    }

    @Override
    public void execCommand(RedisCommand command, InvokeHandler invokeHandler, Object... params) {
        // 封装参数
        RedisRequest redisRequest = new RedisRequest();
        redisRequest.addParam(command);
        redisRequest.addManyParam(params);
        // eventLoop上实例化一个Promise，用于后接受回复
        Promise<Object> promise = channel.eventLoop().newPromise();
        // 添加回调监听器
        promise.addListener(new GenericFutureListener<io.netty.util.concurrent.Future<? super Object>>() {
            @Override
            public void operationComplete(io.netty.util.concurrent.Future<? super Object> future) throws Exception {
                Object obj = null;
                Throwable throwable = null;
                try {
                    obj = future.get();
                } catch (Throwable er) {
                    throwable = er;
                }
                invokeHandler.handle(throwable, obj);
            }
        });

        // 发送消息
        channel.writeAndFlush(redisRequest);
    }


    @Override
    public Future<String> auth(String password) {
        return new FutureTask<String>(() -> {
           // RedisResponse execCommand (RedisCommand.AUTH, password);
            // TODO
            return null;
        });
    }

    @Override
    public void auth(String password, InvokeHandler handler) {

    }

    @Override
    public Future<String> echo(String message) {
        return null;
    }

    @Override
    public void echo(String message, InvokeHandler handler) {

    }

    @Override
    public Future<String> ping() {
        return null;
    }

    @Override
    public void ping(InvokeHandler invokeHandler) {

    }

    @Override
    public Future<String> quit() {
        return null;
    }

    @Override
    public void quit(InvokeHandler invokeHandler) {

    }

    @Override
    public Future<String> select(int dbNumber) {
        return null;
    }

    @Override
    public void select(int dbNumber, InvokeHandler invokeHandler) {

    }

    private Object getValue(RedisResponse redisResponse) {
        if (redisResponse == null) {
            return null;
        }
        if (redisResponse instanceof StatusResponse) {
            return redisResponse;
        }

        // TODO
        return null;
    }
}

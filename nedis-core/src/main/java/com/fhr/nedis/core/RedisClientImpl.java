package com.fhr.nedis.core;

import com.fhr.nedis.core.command.RedisCommand;
import com.fhr.nedis.core.protocol.request.RedisRequest;
import com.sun.org.apache.regexp.internal.RE;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import java.util.concurrent.Future;

/**
 * Created by Huaran Fan on 2018/12/1
 *
 * @description
 */
public class RedisClientImpl implements RedisClient {
    private final Channel channel;

    public RedisClientImpl(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Future execCommand(RedisCommand command, Object... params) {
        // 封装参数
        RedisRequest redisRequest = new RedisRequest();
        redisRequest.addParam(command);
        redisRequest.addManyParam(params);
        // eventLoop上实例化一个Promise，用于后面接受回复
        Promise<Object> promise = channel.eventLoop().newPromise();
        redisRequest.setPromise(promise);
        // TODO 完成redis-response到正常数据的转换

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
        // TODO 完成redis-response到正常数据的转换
        // 添加回调监听器
        promise.addListener(future -> {
            Object obj = null;
            Throwable throwable = null;
            try {
                obj = future.get();
            } catch (Throwable er) {
                throwable = er;
            }
            invokeHandler.handle(throwable, obj);
        });
        redisRequest.setPromise(promise);

        // 发送消息
        channel.writeAndFlush(redisRequest);
    }


    @Override
    public Future<String> get(String key) {
        return execCommand(RedisCommand.GET, key);
    }

    @Override
    public void get(String key, InvokeHandler handler) {
        execCommand(RedisCommand.GET, key, handler);
    }

    @Override
    public Future<Integer> set(String key, String value) {
        return execCommand(RedisCommand.SET, key);
    }

    @Override
    public void set(String key, String value, InvokeHandler handler) {
        execCommand(RedisCommand.SET, key, handler);
    }
}

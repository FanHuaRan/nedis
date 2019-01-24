package com.fhr.nedis.core.protocol;

import com.fhr.nedis.core.protocol.request.RedisRequest;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.Promise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Fan Huaran
 * created on 2019/1/22
 * @description 核心逻辑
 */
public class RedisDuplexHandler extends ChannelDuplexHandler {
    private static class Entry {
        private final Promise<Object> promise;

        // start nano time.
        private final long enqTime;

        public Entry(Promise<Object> promise, long enqTime) {
            this.promise = promise;
            this.enqTime = enqTime;
        }

    }

    private final Queue<Entry> requestQueue = new LinkedList<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Entry entry = requestQueue.poll();
        entry.promise.trySuccess(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        // TODO 状态校验
        RedisRequest redisRequest = (RedisRequest) msg;
        requestQueue.offer(new Entry(redisRequest.getPromise(), System.nanoTime()));
        ctx.writeAndFlush(redisRequest);
    }
}

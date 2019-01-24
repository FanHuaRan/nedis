package com.fhr.nedis.core;

import com.fhr.nedis.core.protocol.RedisDuplexHandler;
import com.fhr.nedis.core.protocol.request.RedisRequestEncoder;
import com.fhr.nedis.core.protocol.response.RedisResponseDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Huaran Fan on 2018/12/1
 *
 * @description
 */
public class RedisClientBuilder {
    private String host;

    private int port;

    public String getHost() {
        return host;
    }

    public RedisClientBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisClientBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public RedisClient build() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    // TODO 空闲状态监测
                    ch.pipeline().addLast(new RedisResponseDecoder());// 响应解码器
                    // TODO ping-pong型心跳处理器和断线重连
                    ch.pipeline().addLast(new RedisRequestEncoder());// 请求编码器
                    // 核心处理器
                    ch.pipeline().addLast(new RedisDuplexHandler());
                }
            });

            ChannelFuture future = bootstrap.connect(host, port).sync();
            Channel channel = future.channel();
            return new RedisClientImpl(channel);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

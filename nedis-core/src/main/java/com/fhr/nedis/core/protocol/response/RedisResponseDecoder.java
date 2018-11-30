package com.fhr.nedis.core.protocol.response;

import com.sun.org.apache.regexp.internal.RE;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class RedisResponseDecoder extends ByteToMessageDecoder {
    /**
     * 一个消息的长度至少都是>=3的
     */
    private static final int BASIC_LENTH = 3;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() < BASIC_LENTH) {// 可能有多条数据，循环读取
            byteBuf.markReaderIndex();// 标记当前开始读取的位置，方便在半包时回滚
            byte typeCode = byteBuf.readByte();
            ResponseDecoder decoder = ResponeDecoders.getResponseDecoder(typeCode);
            RedisResponse redisResponse = decoder.decode(byteBuf);
            if (redisResponse != null) {// 解析成功
                list.add(redisResponse);
            } else {
                byteBuf.resetReaderIndex();// 半包情况，回滚index,下次再读取
                return;
            }
        }
    }
}

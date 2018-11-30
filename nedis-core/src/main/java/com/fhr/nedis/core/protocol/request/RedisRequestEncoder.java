package com.fhr.nedis.core.protocol.request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.fhr.nedis.core.protocol.ProtocolConstant.DELIMIT;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class RedisRequestEncoder extends MessageToByteEncoder<RedisRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RedisRequest msg, ByteBuf out) throws Exception {
        List<Object> params = msg.getParams();
        out.writeInt(params.size());// 参数数量
        out.writeCharSequence(DELIMIT, StandardCharsets.UTF_8);// 分隔符

        for(Object param : params){
            byte[] bytes = String.valueOf(param).getBytes(StandardCharsets.UTF_8);

            out.writeInt(bytes.length);// 写入参数字节长度
            out.writeCharSequence(DELIMIT, StandardCharsets.UTF_8);// 分隔符
            out.writeBytes(bytes);// 写入参数字节
            out.writeCharSequence(DELIMIT, StandardCharsets.UTF_8);// 分隔符
        }
    }
}

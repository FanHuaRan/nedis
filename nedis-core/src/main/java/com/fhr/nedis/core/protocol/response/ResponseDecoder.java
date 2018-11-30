package com.fhr.nedis.core.protocol.response;

import io.netty.buffer.ByteBuf;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description 具体响应解码器，
 */
public interface ResponseDecoder {
    /**
     * 解码
     *
     * @param byteBuf
     * @return 返回null代表数据流未接受完毕
     */
    RedisResponse decode(ByteBuf byteBuf);
}

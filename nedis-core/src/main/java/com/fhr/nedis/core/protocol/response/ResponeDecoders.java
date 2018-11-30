package com.fhr.nedis.core.protocol.response;

import com.fhr.nedis.utils.StringUtils;
import com.sun.org.glassfish.external.statistics.Statistic;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class ResponeDecoders {
    private static Map<Byte, ResponseDecoder> RESPONSE_DECODERS = new ConcurrentHashMap<>();

    static {
        RESPONSE_DECODERS.put((byte)'+', new StatusResponseDecoder());
        RESPONSE_DECODERS.put((byte)'-', new ErrResponseDecoder());
        RESPONSE_DECODERS.put((byte)':', new IntegerResponseDecoder());
        RESPONSE_DECODERS.put((byte)'$', new BulkResponseDecoder());
        RESPONSE_DECODERS.put((byte)'*', new MultiBulkResponseDecoder());
    }

    /**
     * 获取
     *
     * @param prefix
     * @return
     */
    public static ResponseDecoder getResponseDecoder(Byte prefix){
        return RESPONSE_DECODERS.get(prefix);
    }

    /**
     * 状态回复是以+开头，以"\r\n" 结尾的字符串
     */
    protected static final class StatusResponseDecoder implements ResponseDecoder {
        @Override
        public RedisResponse decode(ByteBuf byteBuf) {
            String msg = decodeStringWithCRLF(byteBuf);
            if (msg != null) {
                return new StatusResponse(msg);
            }
            return null;
        }
    }

    /**
     * 错误回复是以-开头，然后紧根错误类型，空格+错误信息+CRLF
     */
    protected static final class ErrResponseDecoder implements ResponseDecoder {

        @Override
        public RedisResponse decode(ByteBuf byteBuf) {
            String errType = decodeStringWithBlank(byteBuf);
            if (errType == null) {
                return null;
            }
            String msg = decodeStringWithCRLF(byteBuf);
            if (msg != null) {
                return new ErrorResponse(errType, msg);
            }
            return null;
        }
    }

    /**
     * 整数回复以:开头，然后是CRLF结尾的字符串表示的整数。
     */
    protected static final class IntegerResponseDecoder implements ResponseDecoder {

        @Override
        public RedisResponse decode(ByteBuf byteBuf) {
            // TODO
            return null;
        }
    }

    /**
     * 批量回复
     */
    protected static final class BulkResponseDecoder implements ResponseDecoder {

        @Override
        public RedisResponse decode(ByteBuf byteBuf) {
            // TODO
            return null;
        }
    }

    /**
     * 多条批量回复
     */
    protected static final class MultiBulkResponseDecoder implements ResponseDecoder {

        @Override
        public RedisResponse decode(ByteBuf byteBuf) {
            // TODO
            return null;
        }
    }


    private static String decodeStringWithCRLF(ByteBuf byteBuf) {
        LinkedList<Byte> byteArrays = new LinkedList<>();
        MutableBoolean readComplete = new MutableBoolean(false);
        byteBuf.forEachByte(value -> {
            if (value == '\n') {
                if (!byteArrays.isEmpty() && byteArrays.getLast().equals('\r')) {
                    byteArrays.removeLast();
                    readComplete.setTrue();
                    return false;
                }
            } else {
                byteArrays.add(value);
            }
            return true;
        });

        if (readComplete.isTrue()) {
            return StringUtils.parse(byteArrays, StandardCharsets.UTF_8);
        }

        return null;
    }

    private static String decodeStringWithBlank(ByteBuf byteBuf) {
        LinkedList<Byte> byteArrays = new LinkedList<>();
        MutableBoolean readComplete = new MutableBoolean(false);
        byteBuf.forEachByte(value -> {
            if (value == ' ') {
                readComplete.setTrue();
                return false;
            } else {
                byteArrays.add(value);
                return true;
            }
        });

        if (readComplete.isTrue()) {
            return StringUtils.parse(byteArrays, StandardCharsets.UTF_8);
        }

        return null;
    }
}

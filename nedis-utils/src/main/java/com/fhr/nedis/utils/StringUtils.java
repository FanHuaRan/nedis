package com.fhr.nedis.utils;

import com.google.common.base.Preconditions;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class StringUtils {

    public static String parse(List<Byte> bytes, Charset charset) {
        Preconditions.checkNotNull(bytes, "bytes must not be null");
        byte[] array = new byte[bytes.size()];
        int indexer = 0;
        for (Byte value : bytes) {
            array[indexer++] = value;
        }
        return new String(array, charset);
    }

}

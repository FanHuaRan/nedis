package com.fhr.nedis.core.protocol.response;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Fan Huaran
 * created on 2018/11/30
 * @description
 */
public class MultiBulkResponse extends RedisResponse {
    private final List<RedisResponse> redisResponses = new LinkedList<>();

    public void addResponse(RedisResponse redisResponse){
        redisResponses.add(redisResponse);
    }

    public List<RedisResponse> getRedisResponses() {
        return redisResponses;
    }

    @Override
    public String toString() {
        return "MultiBulkResponse{" +
                "redisResponses=" + redisResponses +
                '}';
    }
}

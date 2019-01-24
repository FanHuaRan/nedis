package com.fhr.nedis.core;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class RedisClientTest {

    @Test
    public void execCommand() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        RedisClient redisClient = new RedisClientBuilder().setHost("127.0.0.1")
                .setPort(6379)
                .build();
        redisClient.set("key2", "value2");
        redisClient.get("key1");

        countDownLatch.await();

    }
}
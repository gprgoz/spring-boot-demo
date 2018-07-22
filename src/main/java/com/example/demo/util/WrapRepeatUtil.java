package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 封装重复代码的工具类
 * Created by ZhaoYuJie on 2017/12/14.
 */
public class WrapRepeatUtil {
    private static Logger logger = LoggerFactory.getLogger(WrapRepeatUtil.class);

    public static <T> T wrapJedisTryCatch(final Jedis jedis, JedisPool jedisPool, WrapRepeatCallBack<T> wrapRepeatCallBack){
        try {
            return wrapRepeatCallBack.callBack();
        } catch (Exception ce) {
            logger.error("jedis error " + ce.getMessage(), ce);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                logger.error("return Jedis failed", e);
                jedisPool.returnBrokenResource(jedis);
            }
        }

    }
}

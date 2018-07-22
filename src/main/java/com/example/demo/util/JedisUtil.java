package com.example.demo.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ZhaoYuJie on 2017/12/13.
 */
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private static JedisPool jedisPool;

    static {
        Properties properties = new Properties();
        String configFileName = "/redis.properties"; //带/表示从classpath目录下读取，否则是从类所在包下读取
        try {
            properties.load(JedisUtil.class.getResourceAsStream(configFileName));
        } catch (IOException e) {
            logger.error("load redis config file " + configFileName + " error.", e);
        }

        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));
        cfg.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
        cfg.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
        cfg.setMaxWaitMillis(Long.parseLong(properties.getProperty("redis.maxWaitMillis")));
        cfg.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow")));
        cfg.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("redis.testOnReturn")));
        jedisPool = new JedisPool(cfg,properties.getProperty("redis.host"), Integer.parseInt(properties.getProperty("redis.port")));
    }


    private static Jedis getJedis() {
        if (jedisPool != null) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                return jedis;
            } catch (Exception ce) {
                logger.error("getJedis error " + ce.getMessage(), ce);
                if (jedis != null) {
                    jedisPool.returnBrokenResource(jedis);
                    jedis = null;
                }
                return null;
            }
        } else {
            logger.error("jedis pool not initialized.");
        }
        return null;
    }

    public static String get(final String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        final Jedis jedis = getJedis();
        if (jedis == null) {
            return null;
        }

        return WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<String>() {
            @Override
            public String callBack() throws Exception {
                return jedis.get(key);
            }
        });

    }

    public static void set(String key, String value) {
        set(key, value, -1);
    }

    public static void set(final String key, final String value, final int seconds) {
        if (seconds == 0) {
            return;
        }
        final Jedis jedis = getJedis();
        if (jedis == null) {
            return;
        }

        WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<Void>() {
            @Override
            public Void callBack() throws Exception {
                if (seconds > 0) {
                    jedis.setex(key, seconds, value);
                } else if (seconds < 0) {
                    jedis.set(key, value);
                }
                return null;
            }
        });

    }

    public static long incrBy(final String key, final int value) {
        final Jedis jedis = getJedis();
        if (jedis == null) {
            return 0;
        }

        return WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<Long>() {
            @Override
            public Long callBack() throws Exception {
                return jedis.incrBy(key, value);
            }
        });
    }

    /**
     * 将一个值插入到列表头部，value可以重复，返回列表的长度
     * @param key
     * @param values
     * @return 返回列表长度
     */
    public static Long lpush(final String key,final int seconds, final String... values){
        final Jedis jedis = getJedis();

        return WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<Long>() {
            @Override
            public Long callBack() throws Exception {
                Long length = jedis.lpush(key,values);
                if(seconds > 0){
                    jedis.expire(key,seconds);
                }
                return length;
            }
        });
    }

    /**
     * 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
     * @param key
     * @return String
     */
    public static String rpop(final String key){
        final Jedis jedis = getJedis();
        return WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<String>() {
            @Override
            public String callBack() throws Exception {
                return jedis.rpop(key);
            }
        });
    }

    /**
     * 获取列表长度，key为空时返回0
     * @param key
     * @return Long
     */
    public static Long llen(final String key){
        final Jedis jedis = getJedis();

        return WrapRepeatUtil.wrapJedisTryCatch(jedis,jedisPool,new WrapRepeatCallBack<Long>() {
            @Override
            public Long callBack() throws Exception {
                return jedis.llen(key);
            }
        });

    }

    public static void main(String[] args) {

    }

}

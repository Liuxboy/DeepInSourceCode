package com.github.liuxboy.interview.code;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/27 <br>
 * Time: 11:50 <br>
 * Desc: 通过Redis实现分布式锁
 * 主要是两个命令，获取锁用setnx，如果存在则返回失败，说明先有线程创建成功锁，del则是删除key表示释放锁
 * 利用set key的超时机制
 * 有以下问题：
 * （1）lock timeout存在使得锁必须在有效时间之内完成操作，一时超时，其它线程则会创建新锁或者延长改锁
 * （2）redis单机部署的话，可靠性无法保证，如果引入cluster方案，或者sentinel，复杂度会增加，引入更多的问题
 */
public class DistributeLockByRedis {

    private static String LOCK_PREFIX = "LOCK_";
    private static String lockKey = "LOCK_KEY_";
    private Jedis jedis = new Jedis("localhost");

    //基本版
    public void lock() {
        long result;
        while (true) {
            result = jedis.setnx(lockKey, "lock_value");
            if (result == 0) {
                //获取到了锁
                break;
            }
            //没有获取到锁
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        jedis.del(lockKey);
    }

    //改进版一
    public void lock_adv1(long lockTimeout) {
        String result;
        while (true) {
            result = jedis.set(lockKey, "lock_value", "NX", "EX", lockTimeout);
            if (StringUtils.isNoneBlank(result)) {
                //获取到了锁
                return;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void release_adv1() {
        String value = jedis.get(lockKey);
        if ("lock_value".equals(value)) {
            jedis.del(lockKey);
        }
    }

    //改进版二
    public void lock_adv2(long lockTimeout) {
        long lock = 0;
        while (true) {
            long timestamp = System.currentTimeMillis() + lockTimeout;
            lock = jedis.setnx(lockKey, Long.toString(timestamp));
            if (lock == 1 || (System.currentTimeMillis() > Long.parseLong(jedis.get(lockKey))
                    && System.currentTimeMillis() > Long.parseLong(jedis.getSet(lockKey, Long.toString(timestamp))))) {
                break;
            } else {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void release_adv2() {
        if (System.currentTimeMillis() < Long.parseLong(jedis.get(lockKey)))
            jedis.del(lockKey);
    }

    /**
     * 获取分布式锁
     *
     * @param lockName           竞争获取锁key
     * @param acquireTimeoutInMS 获取锁超时时间
     * @param lockTimeoutInMS    锁的超时时间
     * @return 获取锁标识
     */
    public String acquireLockWithTimeout(String lockName, String businessType, long acquireTimeoutInMS, long lockTimeoutInMS) {
        String retIdentifier = null;
        String identifier = UUID.randomUUID().toString();
        String lockKey = LOCK_PREFIX + businessType + lockName;
        long lockExpire = lockTimeoutInMS / 1000;
        long end = System.currentTimeMillis() + acquireTimeoutInMS;
        while (System.currentTimeMillis() < end) {
            if (jedis.setnx(lockKey, identifier) == 1) {
                jedis.expire(lockKey, Math.toIntExact(lockExpire));
                retIdentifier = identifier;
            }
            if (jedis.ttl(lockKey) == -1) {
                jedis.expire(lockKey, Math.toIntExact(lockExpire));
            }
        }
        return retIdentifier;
    }

    /**
     * 释放锁
     *
     * @param lockName
     * @param businessType
     * @param identifier
     * @return
     */
    public boolean releaseLock(String lockName, String businessType, long lockTimeoutInMS, String identifier) {
        String lockKey = LOCK_PREFIX + businessType + lockName;
        return identifier.equals(jedis.getSet(lockKey, identifier))
                && 1 == jedis.expire(lockKey, Math.toIntExact(lockTimeoutInMS));
    }
}

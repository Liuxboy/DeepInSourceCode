package com.github.liuxboy.interview.code;

import redis.clients.jedis.Jedis;

import java.util.UUID;


public class DistributeLockByRedis {

    private static String LOCK_PREFIX = "LOCK_";
    private Jedis jedis = new Jedis("localhost");
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

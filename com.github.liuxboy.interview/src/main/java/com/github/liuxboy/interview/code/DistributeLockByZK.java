package com.github.liuxboy.interview.code;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/27 <br>
 * Time: 11:50 <br>
 * Desc: 通过zk生成分布式锁
 * 方式一：生成临时锁节点，节点生成成功，则表示获得锁，其它线程生成相同节点会失败，则表示未获得锁
 * 然后添加Watcher监视锁节点。当获得锁节点删除锁节点则表示释放锁，同时唤醒其它节点，竞争生成锁节点。
 * 方式二：生成临时有序锁节点，同步访问其父节点的子节点，按自然排序，然后取第一个子节点，
 * 看是否与刚才创建的子节点相等，如果相等则说明拿到锁，不等说明被别人先拿到锁，自己还得再等待。
 * Zk实现的分布式锁与Redis实现的分布式锁对比：
 * （1）锁的占用时间限制：redis就有占用时间限制，而ZooKeeper则没有，最主要的原因是redis目前没有办法知道已经获取锁的客户端的状态，
 * 是已经挂了呢还是正在执行耗时较长的业务逻辑。而ZooKeeper通过临时节点就能清晰知道，如果临时节点存在说明还在执行业务逻辑，
 * 如果临时节点不存在说明已经执行完毕释放锁或者是挂了。由此看来redis如果能像ZooKeeper一样添加一些与客户端绑定的临时键，也是一大好事。
 * （2）是否单点故障：redis本身有很多中玩法，如客户端一致性hash，服务器端sentinel方案或者cluster方案，很难做到一种分布式锁方式能应对所有这些方案。
 * 而ZooKeeper只有一种玩法，多台机器的节点数据是一致的，没有redis的那么多的麻烦因素要考虑。
 */
public class DistributeLockByZK {

    public class DistributedLock {
        private final ZooKeeper zk;
        private final String lockBasePath;
        private final String lockName;
        private String lockPath;
        public DistributedLock(ZooKeeper zk, String lockBasePath, String lockName) {
            this.zk = zk;
            this.lockBasePath = lockBasePath;
            this.lockName = lockName;
        }
        public void lock() throws IOException {
            try {
                lockPath = zk.create(lockBasePath + "/" + lockName, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                final Object lock = new Object();
                synchronized(lock) {
                    while(true) {
                        List<String> nodes = zk.getChildren(lockBasePath, new Watcher() {
                            @Override
                            public void process(WatchedEvent event) {
                                synchronized (lock) {
                                    lock.notifyAll();
                                }
                            }
                        });
                        Collections.sort(nodes);
                        if (lockPath.endsWith(nodes.get(0))) {
                            return;
                        } else {
                            lock.wait();
                        }
                    }
                }
            } catch (KeeperException e) {
                throw new IOException (e);
            } catch (InterruptedException e) {
                throw new IOException (e);
            }
        }

        public void unlock() throws IOException {
            try {
                zk.delete(lockPath, -1);
                lockPath = null;
            } catch (KeeperException e) {
                throw new IOException (e);
            } catch (InterruptedException e) {
                throw new IOException (e);
            }
        }
    }
}

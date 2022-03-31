package com.itheima.user.config;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by migu on 2020/12/16.
 */
@Service
public class RedissionManage {

    //从配置类中获取redisson对象
    @Autowired
    private RedissonClient redisson;

    private static final String LOCK_TITLE = "cell_";

    //加锁 fail, suc, error
    public String acquireLock(String lockName, int expireT) {
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        try {
            if (!tryLock(expireT, key)) {
                return "fail";
            }
            return "suc";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获得锁异常,lockName = " + lockName);
            return "error";
        }
    }

    //锁的释放
    public void releaseLock(String lockName) {
        //必须是和加锁时的同一个key
        String key = LOCK_TITLE + lockName;
        //获取所对象
        RLock mylock = redisson.getLock(key);
        //释放锁（解锁）
        mylock.unlock();
    }

    /**
     * 获取锁
     *
     * @param lockName
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(Integer lockTime, String lockName) throws InterruptedException {
        RLock lock = redisson.getLock(lockName);
        return lock.tryLock(1, lockTime, TimeUnit.SECONDS);
    }
}

package com.github.tangyi.common.core.utils.redis;


import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-22 16:45
 **/
public class RedisLockUtil {

  private static DistributedLocker distributedLocker;

  public static void setLocker(DistributedLocker locker) {
    distributedLocker = locker;
  }


  /**
   * lock(), 拿不到lock就不罢休，不然线程就一直block
   * @param lockKey
   * @return
   */
  public static RLock lock(String lockKey) {
    return distributedLocker.lock(lockKey);
  }

  /**
   * leaseTime为加锁时间，单位为秒
   * @param lockKey
   * @param leaseTime
   * @return
   */
  public static RLock lock(String lockKey, long leaseTime) {
    return distributedLocker.lock(lockKey,leaseTime);
  }

  /***
   * timeout为加锁时间，时间单位由unit确定
   * @param lockKey
   * @param unit
   * @param timeout
   * @return
   */
  public static RLock lock(String lockKey, TimeUnit unit, long timeout) {
    return distributedLocker.lock(lockKey,unit,timeout);
  }

  /**
   *tryLock()，马上返回，拿到lock就返回true，不然返回false。
   * 带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false.
   * @param lockKey
   * @param unit
   * @param waitTime
   * @param leaseTime
   * @return
   */
  public  static boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
    return distributedLocker.tryLock(lockKey,unit,waitTime,leaseTime);
  }

  /**
   * 释放锁
   * @param lockKey
   */
  public static void unlock(String lockKey) {
    distributedLocker.unlock(lockKey);
  }

  /**
   * 释放锁
   * @param lock
   */
  public static void unlock(RLock lock) {
    distributedLocker.unlock(lock);
  }

}

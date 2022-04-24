package com.github.tangyi.common.core.utils.redis;


import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;

/**
 * @author gaokx
 * @Description
 * @create 2021-03-22 16:56
 **/
public interface DistributedLocker {

  RLock lock(String lockKey);

  RLock lock(String lockKey, long leaseTime);

  RLock lock(String lockKey, TimeUnit unit, long timeout);

  boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

  void unlock(String lockKey);

  void unlock(RLock lock);
}

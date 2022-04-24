package com.juyss;

import redis.clients.jedis.Jedis;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PingRedis
 * @Desc: 测试Redis远程连接
 * @package com.juyss
 * @project atguigu-Redis
 * @date 2020/9/23 18:38
 */
public class PingRedis {

    public static void main(String[] args) {
        Jedis jedis=null;
        try {
//            jedis = new Jedis("118.31.244.185", 6379);
            jedis = new Jedis("192.168.112.133", 6379);
            String ping = jedis.ping();
            System.out.println("Redis响应===>"+ping);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

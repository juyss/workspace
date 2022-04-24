package com.atguigu.gmail.gmailzookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Description:
 *  * 1	初始化ZK的多个操作
 * 		1.1	建立ZK的链接
 * 		1.2	创建/atguigu节点并赋值
 * 		1.3	获得该节点的值
 *
 * 2	watch
 * 		2.1	获得值之后(getZnode方法被调用后)设置一个观察者watcher，如果/atguigu该节点的值发生了变化，(A-->B)
 * 			要求通知Client端eclipse，一次性通知
 *
 * @author xialei
 *
 */
public class WatchOne {

    private static final String CONNECTSTRING = "192.168.140.130:2181";
    private static final String PATH = "/atguigu";
    private static final int SESSION_TIMEOUT = 20 * 1000;

    //实例变量
    private ZooKeeper zk;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    /**
     * 通过java程序，新建链接zk
     * @return
     * @throws IOException
     */
    public ZooKeeper startZK() throws IOException {
        return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }


    /**
     * 新建一个znode节点
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void createZNode(String path,String data) throws KeeperException, InterruptedException {
        zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }


    /**
     * 获得当前节点/atguigu的最新值
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getZNode(String path) throws KeeperException, InterruptedException {
        String result = "";
        byte[] data = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    triggerValue(path);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        result = new String(data);
        return result;
    }



    //triggerValue
public void triggerValue(String path) throws KeeperException, InterruptedException {
    String result = "";
    byte[] data = zk.getData(path, false, new Stat());
    result = new String(data);
    System.out.println("triggerValue = "+result);
}


    public static void main(String[] args) throws Exception
    {
        WatchOne watchOne = new WatchOne();
        watchOne.setZk(watchOne.startZK());
        if(watchOne.getZk().exists(PATH,false)==null){
            watchOne.createZNode(PATH,"AAA");
            String zNode = watchOne.getZNode(PATH);
            System.out.println(" zNode ="+ zNode);
        }else {
            System.out.println("this  zNode is created ");
        }
        Thread.sleep(Long.MAX_VALUE);


    }

}

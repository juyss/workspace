package com.atguigu.gmail.gmailzookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 *
 * @Description:
 *1	初始化ZK的多个操作
 * 		1.1	建立ZK的链接
 * 		1.2	创建/atguigu节点并赋值
 * 		1.3	获得该节点的值
 *
 * 2	watchmore
 * 		2.1	获得值之后设置一个观察者watcher，如果/atguigu该节点的值发生了变化，要求通知Client端，一次性通知
 *
 * 3	watchMore
 * 		3.1	获得值之后设置一个观察者watcher，如果/atguigu该节点的值发生了变化，要求通知Client端,继续观察
 * 		3.2	又再次获得新的值的同时再新设置一个观察者，继续观察并获得值
 * 		3.3	又再次获得新的值的同时再新设置一个观察者，继续观察并获得值.。。。。。重复上述过程
 * @author xialei
 * @date
 */
public class WatchMore {

    //实例常量
    private static final String CONNECTION_STRING = "192.168.140.130:2181";
    private static final String PATH = "/atguigu";
    private static final int SESSION_TIMEOUT = 20 * 1000;
    //实例变量
    private ZooKeeper zk = null;
    private  String oldValue = null;
    private  String newValue = null;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    /**
     *
     *
     * @Title: startZK
     * @Description: 获得ZK的session连接对象实例
     * @param @return
     * @param @throws IOException    参数
     * @return ZooKeeper    返回类型
     * @throws
     */
    public ZooKeeper startZK() throws IOException
    {
        return new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event)
            {

            }
        });
    }

    /**
     *
     * @Title: createZnode
     * @Description: 再给定的路径下创建znode节点并赋值
     * @param @param zk
     * @param @param path
     * @param @param data
     * @param @throws KeeperException
     * @param @throws InterruptedException    参数
     * @return void    返回类型
     * @throws
     */
    public void createZnode(String path,String data) throws KeeperException, InterruptedException
    {
        zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     *
     * @Title: getZnode
     * @Description: 获取我们对应节点的值
     * @param @param zk
     * @param @param path
     * @param @return
     * @param @throws KeeperException
     * @param @throws InterruptedException    参数
     * @return String    返回类型
     * @throws
     */
    public String getZnode(String path) throws KeeperException, InterruptedException
    {
        String result = "";

        byte[]  byteArray = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event)
            {
                try
                {
                    triggerValue(path);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        result = new String(byteArray);
        oldValue = result;

        return result;
    }

    public boolean triggerValue(String path) throws KeeperException, InterruptedException
    {
        String result = "";

        byte[] byteArray = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event)
            {
                try
                {
                    triggerValue(path);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        result = new String(byteArray);
        newValue = result;

        if(oldValue.equals(newValue))
        {
            System.out.println("-----------no changes---------0000 ");
            return false;
        }else {
            System.out.println("-----------newValue: "+newValue+"\t oldValue: "+oldValue);
            oldValue = newValue;
            return true;
        }

    }


    public static void main(String[] args) throws Exception
    {
        WatchMore watchMore = new WatchMore();
        watchMore.setZk(watchMore.startZK());

        if(watchMore.getZk().exists(PATH, false) == null)
        {
            watchMore.createZnode(PATH, "AAA");

            String result = watchMore.getZnode(PATH);//AAA


            System.out.println("main(String[]) --------init String result=" + result);

        }else {
            System.out.println("this node has already exists!!!!");
        }

        Thread.sleep(Long.MAX_VALUE);
    }

}

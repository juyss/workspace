package com.atguigu.gmail.gmailzookeeperdemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 *
 * 此处为Client端，CentOS为ZooKeeper的Server端
 *
 * 1	 通过java程序，新建链接zk，类似jdbc的connection，open.session
 * 2	 新建一个znode节点/atguigu并设置为	等同于create /atguigu hello0925 Ids.OPEN_ACL_UNSAFE
 * 3	 获得当前节点/atguigu的最新值			get /atguigu
 * 4	关闭链接
 * xialei
 *
 */
public class HelloZK {

    private static final String CONNECTSTRING = "192.168.140.130:2181";
    private static final String PATH = "/atguigu";
    private static final int SESSION_TIMEOUT = 20 * 1000;


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
     * @param zk
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void createZNode(ZooKeeper zk,String path,String data) throws KeeperException, InterruptedException {
        zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
    }

    /**
     * 获得当前节点/atguigu的最新值
     * @param zk
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getZNode(ZooKeeper zk,String path) throws KeeperException, InterruptedException {
        String result = "";
        byte[] data = zk.getData(path, false, new Stat());
        result = new String(data);
        return result;
    }

    /**
     * 关闭链接
     * @param zk
     * @throws InterruptedException
     */
    public void stopZK(ZooKeeper zk) throws InterruptedException {
        if(zk != null){
           zk.close();
        }
    }

    public static void main(String[] args) throws Exception
    {
        HelloZK helloZK = new HelloZK();
        ZooKeeper zk = helloZK.startZK();
        if(zk.exists(PATH,false)==null){
            helloZK.createZNode(zk,PATH,"java190401");
            String zNode = helloZK.getZNode(zk, PATH);
            System.out.println(" zNode ="+ zNode);
        }else {
            System.out.println("this  zNode is created ");
        }
        helloZK.stopZK(zk);

    }
}

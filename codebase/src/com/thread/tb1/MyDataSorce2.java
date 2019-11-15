package com.thread.tb1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-11 15:56
 */
public class MyDataSorce2 {

    private LinkedList<Connection> pool=new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;

    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";

    private static final String USER= "";

    private static final String URL= "";

    private static final String PASSWORD= "";

    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();

    static{
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSorce2() {
        //初始化数据库连接池的大小
        for(int i=0;i<INIT_CONNECTIONS;i++) {
            try {

                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnect() {
        Connection result = null;
        //不能直接在方法上加synchronized，因为我们锁的是pool
        lock.lock();
        try{
        //这里也不能用if
        while (pool.size() <= 0) {
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!pool.isEmpty()) {
            result = pool.removeFirst();
        }
    }finally {
            lock.unlock();
        }
        return result;
    }

    public void release(Connection conn){

        //释放数据库连接，其实就是将这个链接放回连接池
        if(conn!=null){
           lock.lock();
           try {
               pool.addLast(conn);
               c1.signalAll();
           }finally {
               lock.unlock();
           }
        }
    }
}

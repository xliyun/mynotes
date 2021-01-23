package com.thread.tb1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-05-11 15:56
 */
public class MyDataSorce {

    private LinkedList<Connection> pool=new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;

    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";

    private static final String USER= "";

    private static final String URL= "";

    private static final String PASSWORD= "";

    static{
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSorce() {
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

    public Connection getConnect(){
        Connection result=null;
        //不能直接在方法上加synchronized，因为我们锁的是pool
        synchronized (pool) {
            //这里也不能用if
            while (pool.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(!pool.isEmpty()){
                result=  pool.removeFirst();
            }
        }
        return result;
    }

    public void release(Connection conn){
        //释放数据库连接，其实就是将这个链接放回连接池
        if(conn!=null){
            synchronized (pool){
                pool.addLast(conn);
                notifyAll();
            }
        }
    }
}

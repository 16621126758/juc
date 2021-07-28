package com.zhz.demo01;

/**
 * @Class: SaleTicketDemo01
 * @description:
 * @Author: hongzhi.zhao
 * @Date: 2021-07-26 23:51
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *真正的多线程开发公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属操作
 * 1.属性和方法
 */
public class SaleTicketDemo01 {

//      并发：多线程操作同一个资源类
public static void main(String[] args) {

    Ticket ticket = new Ticket();
    new Thread(()->{
        for (int i = 0; i < 60; i++) {
            ticket.sale();
        }
    },"A").start();
    new Thread(()->{
        for (int i = 0; i < 60; i++) {
            ticket.sale();
        }
    },"b").start();
    new Thread(()->{
        for (int i = 0; i < 60; i++) {
            ticket.sale();
        }
    },"c").start();
}

}


class Ticket{
    private int number = 50;

    Lock lock = new ReentrantLock();

//    买票的方式
//    Lock 三部曲  1.new ReentrantLock();  2.lock.lock try上业务代码 3.finally 解锁
    public  void sale(){

        lock.lock();

        try {
            if (number> 0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"票"+"剩余："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
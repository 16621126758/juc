package com.zhz.px;

/**
 * @Class: A
 * @description: 线程之间的消费问题，生产者，消费者问题
 * 线程交替执行 A B操作同一个变量， num=0
 * A num+1
 * B num-1
 * @Author: hongzhi.zhao
 * @Date: 2021-07-28 22:46
 */
public class A {

    public static void main(String[] args) {
         Date date = new Date();


         new Thread(()->{
             for (int i = 0; i < 10; i++) {
                 try {
                     date.increment();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         },"A").start();

         new Thread(()->{
             for (int i = 0; i < 10; i++) {
                 try {
                     date.decrement();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         },"B").start();
    }

}

//判断等待,业务,通知
class Date{   //数字资源类

    private int number = 0;

    public  synchronized void increment() throws InterruptedException {
        if (number!=0){
//          等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
//          通知其他线程 我+1完毕了
        this.notify();
    }

    public synchronized void decrement() throws InterruptedException {
        if (number==0){
//          等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
//         通知其他线程  我-1完了
        this.notify();
    }

}
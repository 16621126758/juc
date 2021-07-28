package com.zhz.demo01;

/**
 * @Class: SaleTicketDemo2
 * @description:
 * @Author: hongzhi.zhao
 * @Date: 2021-07-27 22:51
 */
public class SaleTicketDemo2 {

}

class Ticket2{
    private int number = 30;
    public void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"票"+"剩余："+number);
        }
    }
}

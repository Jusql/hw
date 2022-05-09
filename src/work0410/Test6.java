package work0410;

import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//存在线程无法停止和售卖过程中暗中顺序而比赛随机的问题

public class Test6{
    public static void main(String[] args) {
        Ticket ticket=new Ticket(5);
        Seller s=new Seller(ticket);

        Thread thread1=new Thread(s,"窗口1");
        Thread thread2=new Thread(s,"窗口2");
        Thread thread3=new Thread(s,"窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static class Ticket{
        private int count;//当前票数
        private int maxCount;//车票总数
        private int num;

        public Ticket(int maxCount){
            this.maxCount=maxCount;
            this.count=maxCount;
        }
        //抢票方法
        ReentrantLock lock=new ReentrantLock();
        Condition sellerCondition=lock.newCondition();//等待队列


        public void sell() {
            while (true){
                    lock.lock();
                    count--;
                    num = maxCount - count;
                    System.out.println(Thread.currentThread().getName() + "售出第" + num + "张票，" + "还剩" + count + "张票");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sellerCondition.signal();
                    if (count==0){
                        System.out.println("车票已售罄");
                        break;
                    }
                    try {
                        sellerCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }

            }


//            lock.lock();
//            try {
//                while (count == 0) {
//                    System.out.println("车票已售罄");
//                    sellerCondition.await(10,TimeUnit.MILLISECONDS);
//                }
//                Thread.sleep(500);
//                count--;
//                num=maxCount-count;//卖出第num张票
//                System.out.println(Thread.currentThread().getName()+"卖出第"+num+"张票"+",剩余"+count+"张票");
//
//                sellerCondition.signal();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
    }


    static class Seller implements Runnable{
        private Ticket ticket;
        public Seller(Ticket ticket){
            this.ticket=ticket;
        }

        @Override
        public void run(){
                this.ticket.sell();
        }
    }



}
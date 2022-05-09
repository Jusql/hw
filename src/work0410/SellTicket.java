package work0410;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假设火车站有三个窗口同时卖票，设计程序模拟同时卖票的场景（每个线程分别代表一个窗口）
 */
public class SellTicket extends Thread {
    private static int ticketNum = 10;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
//            condition.signal();
            if (ticketNum <= 0) {
                System.out.println(Thread.currentThread().getName()+"已经没有票了！");
                reentrantLock.unlock();
                break;
            } else {
                System.out.println("还有" + ticketNum + "张票！");
                ticketNum--;
                System.out.println(Thread.currentThread().getName() + "已买出1票");
                System.out.println("还剩" + ticketNum + "张票！");
//                System.out.println();
            }
//            try {
//                condition.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            reentrantLock.unlock();
        }
    }
    public static void main(String[] args) {
        SellTicket ticket = new SellTicket();
        Thread t1 = new Thread(ticket," 111：");
        Thread t2 = new Thread(ticket,"      2222：");
        Thread t3 = new Thread(ticket,"            33333333：");
        t1.start();
        t2.start();
        t3.start();
    }
}

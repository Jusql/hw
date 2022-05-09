package work0403;

import java.util.concurrent.Semaphore;

/**
 * 6、假设火车站有三个窗口同时卖票，设计程序模拟同时卖票的场景（每个线程分别代表一个窗口）
 */
class Ticket implements Runnable {
    private int ticketNum =110000;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
//                notifyAll();
                if (ticketNum == 0) {
                    System.out.println(Thread.currentThread()+"已经没有票了！");
                    break;
                } else {
                    System.out.println("还有" + ticketNum + "张票！");
                    ticketNum--;
                    System.out.println(Thread.currentThread().getName() + "已买出1票");
                    System.out.println("还剩" + ticketNum + "张票！");
                    System.out.println();
                }
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }

    }
}

public class Sell {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket, "**     1111**");
        Thread t2 = new Thread(ticket, "**   2222**");
        Thread t3 = new Thread(ticket, "**3333**");


        t1.start();
        t2.start();
        t3.start();

    }
}


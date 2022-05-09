package work0410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作业，使用ReentrantLock、ReadWriteLock、Condition、CountDownLatch等JUC工具实现如下题目：
 * 1、有a、b两个线程，要求a执行完才开始执行b,b执行完再开始执行a
 * 2、两个线程轮流打印数字，1到100
 * 3、写两个线程，一个线程打印1-52,另一个线程打印A-Z,打印顺序为12A34B56C.....5152Z
 * 4、启动三个线程，线程ID分别设置成A、B、C,每个线程将自己的ID打印5遍，打印顺序为ABCABC....
 * 5、编写10个线程，第一个线程从1加到10,第二个从2加到20....第十个从91加到100,最后把10个线程的结果想加
 * 6、假设火车站有三个窗口同时卖票，设计程序模拟同时卖票的场景（每个线程分别代表一个窗口）
 * 7、实现一个多生产者、多消费者模型
 */


public class Q1 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println("a线程");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("a线程结束");
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            lock.lock();
            condition.signalAll();
            System.out.println("b线程");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new ThreadA();
        Thread t2 = new ThreadB();
        t1.start();
        t2.start();
    }

}

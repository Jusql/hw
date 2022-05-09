package work0403;

import org.omg.CORBA.ObjectHelper;

/**
 * 1、有a、b两个线程，要求a执行完才开始执行b,b执行完再开始执行a
 */


public class Q1 {
    static Object obj = new Object();
    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                obj.notify();
                System.out.println("a线程");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a线程结束");

            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                obj.notify();
                System.out.println("b线程");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadA();
        Thread t2 = new ThreadB();
        t1.start();
        t2.start();
    }

}

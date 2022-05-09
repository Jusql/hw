package work0410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 启动三个线程，线程ID分别设置成A、B、C,每个线程将自己的ID打印5遍，打印顺序为ABCABC....
 */

public class Q4 {
    static private int flag = 0;
    static ReentrantLock r1 = new ReentrantLock();
    static Condition c1 = r1.newCondition();

    static class A implements Runnable {
        @Override
        public void run() {
            try {
                r1.lock();
                for (int i = 1; i <= 5; i++) {
                    while (flag != 0) {
                      c1.await();
                    }
                    System.out.print(Thread.currentThread().getName() + " ");
                    flag = 1;
                    c1.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r1.unlock();
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            try {
                r1.lock();
                for (int i = 1; i <= 5; i++) {
                    while (flag != 1) {
                        c1.await();
                    }
                    System.out.print(Thread.currentThread().getName() + " ");
                    flag = 2;
                    c1.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r1.unlock();
            }
        }
    }

    static class C implements Runnable {
        @Override
        public void run() {
            try {
                r1.lock();
                for (int i = 1; i <= 5; i++) {
                    while (flag != 2) {
                        c1.await();
                    }
                    System.out.print(Thread.currentThread().getName() + " ");
                    flag = 0;
                    c1.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                r1.unlock();
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        Thread t1 = new Thread(a, "A");
        B b = new B();
        Thread t2 = new Thread(b, "B");
        C c = new C();
        Thread t3 = new Thread(c, "C");
        t1.start();
        t2.start();
        t3.start();
    }
}

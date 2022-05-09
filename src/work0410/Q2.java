package work0410;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程轮流打印数字，1到100
 */
class T1 implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    private static int  i = 1;

    @Override
    public void run() {
        while (true) {
            if (i <= 100) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ":" + i);
                ++i;
                try {
                    condition.await(10,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            } else {
                break;
            }
        }
    }
}

public class Q2 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        Thread t0 = new Thread(t1);
        Thread t2 = new Thread(t1);
        t0.setName("thread 1__");
        t2.setName("thread 2__");
        t0.start();
        t2.start();
    }
}

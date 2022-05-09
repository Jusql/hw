package work0410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写两个线程，一个线程打印1-52,另一个线程打印A-Z,打印顺序为12A34B56C.....5152Z
 */
public class Q3 {
    static ReentrantLock r1 = new ReentrantLock();
    static Condition c1 = r1.newCondition();

    static class PutChar implements Runnable {
        @Override
        public void run() {
            try {
                r1.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) ('A' + i) + " ");
                    c1.signalAll();
                    try {
                        if (i != 25) {
                            c1.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                r1.unlock();
            }
        }
    }

    static class PutNum implements Runnable {
        @Override
        public void run() {
            try {
                r1.lock();
                for (int i = 1; i <= 52; i++) {
                    System.out.print(i + " ");
                    if (i % 2 == 0) {
                        c1.signalAll();
                        try {
                            c1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                r1.unlock();
            }
        }
    }

    public static void main(String[] args) {
        PutNum putNum = new PutNum();
        PutChar putChar = new PutChar();
        new Thread(putNum).start();
        new Thread(putChar).start();
    }
}

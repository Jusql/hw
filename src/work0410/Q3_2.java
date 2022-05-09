package work0410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Q3_2 {
    public static void main(String[] args) {
        ReentrantLock r1 = new ReentrantLock();
        Condition c = r1.newCondition();

        new Thread(() -> {
            try {
                r1.lock();
                for (int i = 1; i <= 52; i++) {
                    System.out.print(i + " ");
                    if (i % 2 == 0) {
                        c.signalAll();
                        try {
                            if(i!=52)
                                c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                r1.unlock();
            }
        }).start();
                new Thread(() -> {
                    try {
                        r1.lock();
                        for (int i = 0; i < 26; i++) {
                            System.out.print((char) ('A' + i) + " ");
                            c.signalAll();
                            try {
                                if (i != 25){
                                    c.await();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        r1.unlock();
                    }

                }).start();

    }

}


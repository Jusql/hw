package work0410;

import java.util.concurrent.CountDownLatch;

/**
 * 编写10个线程，第一个线程从1加到10,第二个从2加到20....第十个从91加到100,最后把10个线程的结果想加
 */


public class Accumulator extends Thread {
    public static int result = 0;
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static class SubClass extends Thread {
        int sum = 0;
        int flag = 0;

        public SubClass(int flag) {
            this.flag = flag;
        }
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                sum += i + flag * 10;
            }
            System.out.println(getName() + "---->" + sum);
            result += sum;
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            SubClass subClass = new SubClass(i);
            subClass.start();
        }
        countDownLatch.await();
        System.out.println(result);
        System.out.println(System.nanoTime() -start);
    }
}

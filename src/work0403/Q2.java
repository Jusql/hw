package work0403;

//class B extends Thread{
//
//    @Override
//    public void run() {
//        int i = 0;
//        i++;
//        System.out.println(i);
//    }
//}

/**
 * 2、两个线程轮流打印数字，1到100
 */
class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}

public class Q2 extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Number n = new Number();
        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);
        t1.start();
        t2.start();
//        n.run();

    }
//        t1.run();
//        t2.run();
}

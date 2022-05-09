package work0417;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + " :" + i);
        }
    }
}
class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0)
                System.out.println(Thread.currentThread().getName() + " :" + i);
        }
    }
}
public class ThreadPool {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println(executorService.getClass());
        executorService.execute(new NumberThread());
        executorService.execute(new NumberThread1());
        executorService.shutdown();

    }
}

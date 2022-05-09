package work0417;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {
    //final成员变量表示常量，只能被赋值一次，赋值后值不再改变。
    private static final int queueSize = 5;
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(queueSize);
    private static final int produceSpeed = 2000;//生产速度(越小越快)
    private static final int consumeSpeed = 10;//消费速度(越小越快)

    //生产者
    public static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {

                try {

                        System.out.println("老板准备炸油条了，架子上还能放：" + (queueSize - queue.size()) + "根油条");
                        queue.put("1根油条");
                        System.out.println("老板炸好了1根油条，架子上还能放：" + (queueSize - queue.size()) + "根油条");
                        Thread.sleep(produceSpeed);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者
    public static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {

                        System.out.println(Thread.currentThread().getName() +"准备买油条，架子上还剩" + queue.size() + "根油条");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() +"买到1根油条，架子上还剩" + queue.size() + "根油条");
                        Thread.sleep(consumeSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {


        Thread producer1 = new Thread(new Producer());
        Thread consumer1 = new Thread(new Consumer());
        Thread producer2 = new Thread(new Producer());
        Thread consumer2 = new Thread(new Consumer());
        Thread producer3 = new Thread(new Producer());
        Thread consumer3 = new Thread(new Consumer());

        producer1.setName("p1");
        producer2.setName("p2");
        producer3.setName("p3");
        consumer1.setName("c1");
        consumer2.setName("c2");
        consumer3.setName("c3");
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);
        executorService.shutdown();


    }
}

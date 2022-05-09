package work0417;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Modle {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(new Producer("生产者"));
        service.execute(new Consumer("消费者"));
        service.execute(new Shop("商店"));
        service.shutdown();
//        consumerAndShop.execute(new Shop("商店"));
//        consumerAndShop.execute(new Consumer("消费者"));
//        consumerAndShop.shutdown();
//         producer.shutdown();
//        consumer.execute(new Consumer("消费者"));
//        shop.execute(new Shop("商店"));
//        shop.shutdown();
//        consumer.shutdown();
    }

    public static final int queueSize = 200;//生产者最大库存
    public static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(queueSize);
    public static final int SqueueSize = 30;//商店最大库存
    public static final ArrayBlockingQueue<String> Squeue = new ArrayBlockingQueue<>(SqueueSize);

    //    生产者********************************************************************************************
    public static class Producer implements Runnable {
        private int resource = 2000;
        private final int producerTime = 50;//生产时间
        private final int minCount = resource / 20;
        private String Name;

        public Producer(String Name) {
            this.Name = Name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(Name);
            try {
                while (true) {
                    if (resource >= Shop.price && queue.size() < minCount) {
                        resource -= Shop.price;
                        System.out.println("仓库货物不足，开始生产......");
                        queue.put("一件商品");
                        System.out.println(Thread.currentThread().getName() + "生产1件" + "商品，库存" + queue.size() + "件商品......");
                        Thread.sleep(producerTime);
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //商店*********************************************************************************************************
    public static class Shop implements Runnable {
        private final int minCount = 100;
        private final int shopTime = 100;//进货时间
        private String Name;
        private static final int price = 20;//单价

        public Shop(String Name) {
            this.Name = Name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(Name);
            try {
                while (queue.size() != 0 && Squeue.size() < SqueueSize) {
                    if (Squeue.size() < minCount && !queue.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + "库存不足，开始进货......");
                        queue.take();
                        Squeue.put("1件商品");
                        System.out.println(Thread.currentThread().getName() + "新上了1件商品，" + "商店还剩" + Squeue.size() + "件商品...");
                        Thread.sleep(shopTime);
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费者************************************************************************************************************************
    public static class Consumer implements Runnable {
        private final int consumeTime = 500;
        private int money = 200;
        private String Name;

        public Consumer(String Name) {
            this.Name = Name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(Name);
            try {
                while (true) {
                    if (money > Shop.price && !queue.isEmpty()) {
                        System.out.println("钱包管够，可以购买！");
                        Squeue.take();
                        System.out.println("消费者购买1个商品，商店还剩" + Squeue.size() + "件商品");
                        money -= Shop.price;
                        Thread.sleep(consumeTime);
                    } else {
                        System.out.println("钱包瘪了！ 或 商店无商品了");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package work0417;


//import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作业：
 * 1、有三个角色：商店、生产方、消费方
 * 商店：存储商品，制定 商品价格，卖商品
 * 生产方：生产商品（制定生产每个商品的时间，如果生产的商品没有储存到商店则自己存下来），扣减资源（钱）
 * 消费方：买商品，扣减经费，消耗商品
 * <p>
 * 根据上述角色，设计一个生产者消费者模型，并且使用JUC并发工具（例如：BlockingQueue、线程池等）等）
 */
public class ProducerConsumerTest {
    //唯一的商店    只能储存50件商品
    public static Shop shop = new Shop(0);


   public static class Producer implements Runnable {
        private int stuff = 100;
        private int produceTime = 10;
        private int depository = 0;


        @Override
        public void run() {
//    当商店背后的工厂有足够的原材料来生产一件产品时就去生产（进货）
            while (stuff - shop.getProductPrice() > 0) {
                if (shop.isFull()) {
                    try {
                        shop.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                if (shop.isImportProduct()){
                    try {
                        System.out.println("商店需要" +(shop.productMax- shop.queue.size())+"件商品，" + "工厂开始生产商品");
                        shop.queue.put("1件商品");
                        shop.productNum++;
                        stuff -= shop.getProductPrice();
                        System.out.println("已生产一件商品"+"商店还需要" +(shop.productMax- shop.queue.size())+"件商品");
                        Thread.sleep(produceTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     *
     *
     */
   public static class Shop {
       private int productPrice = 12;
        private int productMax = 50;
        private int productNum = 0;
       public static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(shop.productMax);



        private int getProductPrice() {
            return productPrice;
        }

        private void setProductPrice(int productPrice) {
            this.productPrice = productPrice;
        }

        private int getProductNum() {
            return productNum;
        }

        private void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public Shop(int productNum) {
            this.productNum = productNum;
        }

        private boolean isFull() {
            boolean flag = true;
            if (shop.productNum != shop.productMax)
                flag = false;
            return flag;
        }

        private boolean isImportProduct() {
            boolean flag = true;
            if (isFull()) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "库存已满，进货失败");
            }

            return flag;

        }

       public boolean notEmpty() {
            boolean flag = true;
            if(productNum != 0){
                flag=false;
            }
            return flag;
       }
   }

   public static class Comsumer implements Runnable {
        private int money = (int) (Math.random() * 100);
        private int consumeSpeed = 100;


        @Override
        public void run() {
            while(shop.notEmpty()){
                try {
                    System.out.println("货物架上还剩" + shop.queue.size() + "件商品");
                    shop.queue.take();
                    System.out.println(Thread.currentThread().getName()+"买了一件商品，"+"货物架上还剩" + shop.queue.size() + "件商品");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(consumeSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
//        ArrayBlockingQueue   fifo..
//
        Thread t1 = new Thread(new Producer());
        t1.start();
        Thread t2 = new Thread(new Comsumer());
        t2.start();
//


    }
}

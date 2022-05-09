package work0410;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//存在生产和消费过程中不随机使用线程的问题
public class Test7 {
    public static void main(String[] args) {
        Goods goods=new Goods(10000);
        Producer producer=new Producer(goods);
        Consumer consumer=new Consumer(goods);
        List<Thread> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            Thread thread=new Thread(consumer,"消费者"+i);
            list.add(thread);
        }
        for (int i=0;i<5;i++){
            Thread thread=new Thread(producer,"生产者"+i);
            list.add(thread);
        }
        for (Thread thread : list){
            thread.start();
        }
    }

    static class Goods {
        private String name;//产品名
        private int count;//产品数
        private int maxCount;//最大产品数
        private Lock lock = new ReentrantLock();
        private Condition consumerCondition = lock.newCondition();//消费者等待队列

        private Condition producerCondition = lock.newCondition();//生产者等待队列

        public Goods(int maxCount) {
            this.maxCount = maxCount;
        }


//        生产方法
        public void setGoods(String name) {
            lock.lock();
            try {
                while (count == maxCount) {
                    System.out.println(Thread.currentThread().getName() + "商品上限，暂停生产");
                    producerCondition.await();
                }
//                Thread.sleep(500);
                count++;
                this.name=name;
                System.out.println(Thread.currentThread().getName()+"生产"+toString());
                consumerCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }


        }

//        消费方法
        public void getGoods(){
            lock.lock();

            try {
                while (count == 0) {
                    System.out.println("商品未上架");
                    consumerCondition.await();
                }
//                Thread.sleep(500);
                count--;
                System.out.println(Thread.currentThread().getName()+"购买"+toString());
                producerCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }

        @Override
        public String toString(){
            return name+"  "+"产品库存"+count+"个";
        }

    }


    static class Producer implements Runnable{
        private Goods goods;
        public Producer(Goods goods){
            this.goods=goods;
        }
        @Override
        public void run(){
            while (true){
                this.goods.setGoods("篮球");
            }
        }
    }


    static class Consumer implements Runnable{
        private Goods goods;
        public Consumer(Goods goods){
            this.goods=goods;
        }
        @Override
        public void run() {
            while (true) {
                this.goods.getGoods();
            }
        }
    }
}

package work0417;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Work6 {

    public static Shop SHOP = new Shop(15,18);

    static class Producer implements Runnable {
        int resources = (int) (Math.random()*100%100 + 100);
        int productionTime = (int) (Math.random()*100%500 + 1000);
        int depot = 0;

        @Override
        public void run() {
            while (resources - SHOP.getPrice() > 0) {
                try {
                    Shop.lock.writeLock().lock();
                    if(SHOP.isFull()) {
                        Shop.W_COND.await();
                        continue;
                    }


                    if(SHOP.importProduct()) {
                        System.out.println("生产者:" + Thread.currentThread().getName() + " 资源：" + resources + " 库存：" + depot);
                        if(depot == 0) {
                            resources -= SHOP.getPrice();
                        }
                        else {
                            depot--;
                        }
                    }
                    else {
                        depot++;
                    }
                    Shop.W_COND.signalAll();
                    Shop.W_COND.await(productionTime, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Shop.lock.writeLock().unlock();
                }
            }
        }
    }


    static class Consumer implements Runnable {
        int money = (int) (Math.random()*100%100 + 100);
        int buyTime = (int) (Math.random()*100%500 + 300);

        @Override
        public void run() {
            while (money - SHOP.getPrice() > 0) {
                try {
                    Shop.lock.writeLock().lock();
                    if(SHOP.isEmpty()) {
                        Shop.W_COND.await();
                        continue;
                    }

                    if(SHOP.exportProduct()) {
                        System.out.println("消费者:" + Thread.currentThread().getName() + " 现金：" + money);
                        money -= SHOP.getPrice();
                    }
                    Shop.W_COND.signalAll();
                    Shop.W_COND.await(buyTime,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Shop.lock.writeLock().unlock();
                }
            }
        }
    }

    static class Shop {
        private final int full;
        private int count = 10;
        private static int price;
        public static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        public static final Condition W_COND = lock.writeLock().newCondition();

        public Shop(int full, int price) {
            this.full = full;
            Shop.price = price;
        }

        public boolean importProduct() {
            boolean res = true;
            if(isFull()) {
                res = false;
                System.out.println("商店：主商店" +" 参与者："+Thread.currentThread().getName()+" 库存：" + count + " 结果：进口失败");
            }
            else
            {
                count++;
                System.out.println("商店：主商店" +" 参与者："+Thread.currentThread().getName()+" 库存：" + count + " 结果：进口");
            }
            return res;
        }

        public boolean isFull() {
            boolean res;
            lock.readLock().lock();
            res = count == full;
            lock.readLock().unlock();
            return res;
        }

        public boolean exportProduct() {
            boolean res = true;
            if(isEmpty()) {
                res = false;
                System.out.println("商店：主商店" +" 参与者："+Thread.currentThread().getName()+" 库存：" + count + " 结果：出口失败");
            }
            else
            {
                count--;
                System.out.println("商店：主商店"+" 参与者："+Thread.currentThread().getName()+" 库存：" + count + " 结果：出口");
            }
            return res;
        }

        public boolean isEmpty() {
            boolean res;
            lock.readLock().lock();
            res = count == 0;
            lock.readLock().unlock();
            return res;
        }

        public int getPrice() {
            return price;
        }

    }


    public Work6() {
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(), "p"+i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(), "c"+i).start();
        }
    }

    public static void main(String[] args) {
        new Work6();
    }

}

package work0410;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个多生产者、多消费者模型
 */

class Productor extends Thread{
    private ProductorAndConsumer productorAndConsumer;

    public Productor(ProductorAndConsumer productorAndConsumer) {
        this.productorAndConsumer = productorAndConsumer;
    }

    @Override
    public void run() {
        System.out.println("开始生产产品！！！");
        while (true){
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            productorAndConsumer.produce();
        }
    }
}
class Consume extends Thread{
    private ProductorAndConsumer productorAndConsumer;

    public Consume(ProductorAndConsumer productorAndConsumer) {
        this.productorAndConsumer = productorAndConsumer;
    }
    @Override
    public void run() {
        System.out.println("开始消费产品！！！");
        while(true){
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            productorAndConsumer.consume();
        }
    }
}
public class ProductorAndConsumer {
    private static int goodNum = 0;
        void increase(){
            goodNum++;
            System.out.println(Thread.currentThread().getName()+"生产"+goodNum+"件商品");
        }
        public synchronized void produce() {

            if(goodNum<20){
                increase();
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        void decrease(){
            goodNum--;
        }

        public synchronized void consume() {
                if(goodNum>0){
                    decrease();
                    System.out.println(Thread.currentThread().getName()+"还剩" +goodNum+"件商品");
                    notify();
                }else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    public static void main(String[] args) {
        ProductorAndConsumer productorAndConsumer = new ProductorAndConsumer();
        Productor productor = new Productor(productorAndConsumer);

        Consume consume = new Consume(productorAndConsumer);
        Consume consume1 = new Consume(productorAndConsumer);
        Consume consume2 = new Consume(productorAndConsumer);
        Consume consume3 = new Consume(productorAndConsumer);
        Consume consume4 = new Consume(productorAndConsumer);
        consume.setName("消费者1");
        consume1.setName("消费者2");
        productor.start();
        consume1.start();
        consume.start();
        consume2.start();
        consume3.start();
        consume4.start();
    }
}

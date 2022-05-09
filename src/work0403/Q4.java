package work0403;

/**
 * 4、启动三个线程，线程ID分别设置成A、B、C,每个线程将自己的ID打印5遍，打印顺序为ABCABC....
 */

//public class PrintA implements Runnable {
//
//    @Override
//    public void run() {
//
//    }
//}
public class Q4 {
    private int flag = 0;
    public synchronized void printA()  {
        try{
        for (int i = 1; i <= 5; i++) {
            while(flag != 0) {
                this.wait();
            }
                System.out.println(Thread.currentThread().getName());
                flag = 1;
                this.notifyAll();
        }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void printB() {
        try {
            for (int i = 1; i <= 5; i++) {
                while (flag != 1) {
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName());
                flag = 2;
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void printC() {
        try {
            for (int i = 1; i <= 5; i++) {
                while (flag != 2) {
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName());
                flag = 0;
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Q4 q = new Q4();

        new Thread(() -> q.printA(), "A").start();

        new Thread(() -> q.printB(), "B").start();

        new Thread(() -> q.printC(), "C").start();

    }
}

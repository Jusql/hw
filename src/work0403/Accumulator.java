package work0403;

/**
 * 5、编写10个线程，第一个线程从1加到10,第二个11从加到20....第十个从91加到100,最后把10个线程的结果相加
 */

public class Accumulator extends Thread {
    public static int sum;
    private final int startNum;

    public Accumulator(int startNum) {
        this.startNum = startNum;
    }

    public static synchronized void add(int num){
        sum += num;
    }
    public void run() {
        int sum = 0;
        for (int i=0; i<10; i++) {
            sum += (startNum + i);
        }
        add(sum);
    }

    public static void main(String[] args) throws Exception {
        Thread[] threadList = new Thread[10];

        for (int i=0; i<10; i++) {
            threadList[i] = new Accumulator((10 * i) + 1);
            threadList[i].start();
        }
        for (int i=0; i<10; i++) {
            threadList[i].join();
        }
        System.out.println("Sum is : " + sum);
    }
}
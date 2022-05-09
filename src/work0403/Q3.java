package work0403;

/**
 * 3、写两个线程，一个线程打印1-52,另一个线程打印A-Z,打印顺序为12A34B56C.....5152Z
 */

/**
 * 写两个线程，第一个线程打印1-52，第二个线程打印A-Z，打印结果为12A34B...5152Z
 */
public class Q3 {
    // true打印数字，false打印字母
    private boolean flag = true;
    // 打印字母
    public synchronized void printNumber(String s) {
        try {
            if (!flag) {
                super.wait();
            }
            System.out.print(s);
            flag = false;
            super.notifyAll();
        } catch (InterruptedException ex) {
        }
    }

    // 打印数字
    public synchronized void printLetter(String s) {
        try {
            if (flag) {
                super.wait();
            }
            System.out.print(s);
            flag = true;
            super.notifyAll();
        } catch (InterruptedException ex) {
        }
    }

    /**
     *线程2:打印A-Z
     */
    public static class LetterPrintThread extends Thread {
        private Q3 printer;

        private LetterPrintThread(String name, Q3 printer) {
            super(name);
            this.printer = printer;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 26; i++) {
                int temp = i + 64;
                char c = (char) temp;
                printer.printLetter(c + " ");
            }
        }

    }

    /**线程1:打印1-52*/
    public static class NumberPrintThread extends Thread {
        private Q3 printer;

        private NumberPrintThread(String name, Q3 printer) {
            super(name);
            this.printer = printer;
        }
        @Override
        public void run() {
            for (int i = 1; i <= 26; i++) {
                String s = (2 * i - 1) + " " + 2 * i + " ";
                printer.printNumber(s);
            }

        }
    }

    public static void main(String[] args) {
        Q3 printer = new Q3();
        new NumberPrintThread("打印数字线程", printer).start();
        new LetterPrintThread("打印字母线程", printer).start();
    }
}
package work0320;

import java.util.Stack;

public class StackOverflow {
    private static void test(){

        for (int i = 0;; i++) {
            int j = i +1;
           test();


        }

    }

    public static void main(String[] args) {

        StackOverflow.test();
    }
}

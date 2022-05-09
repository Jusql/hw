package work0123;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindNumber {
    public static void main(String[] args) {
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        System.out.println("移除前："+list.toString());

//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        Integer removed = list.remove((int)(Math.random() * list.size()));
//        Integer removed = list.remove(10);
        int missNum = findMissNum(start, end, list);
        System.out.println("移除后："+list.toString());
        System.out.println("miss number:" + missNum);
        System.out.println(removed == missNum ? "成功" : "失败");
    }


    static int findMissNum(int start, int end, List<Integer> list) {
        //请实现这个方法

        for (int i = start; i < end; i++) {
//            if(i==20){
//                return i;
//            }
           if (i != list.get(i - 10)) {
                return i;
            }
        }
        return 20;
    }

    @Test
    public void test(){
        int a = 0b10000;
        int i = a >> 1;
        System.out.println(i);

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(11);
        list.add(12);
        list.add(13);
        System.out.println(list.get(3));

    }
}

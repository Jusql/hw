package work0320;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {
    static  List<Long[]> list=new ArrayList<>();
    public static void main(String[] args) {

        while (true){
            for (int i = 0;; i++) {
                Long[] longs = new Long[i];
                list.add(longs);
            }
        }

    }

}

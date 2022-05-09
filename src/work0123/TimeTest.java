package work0123;

import java.net.CacheRequest;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
//        Date date = new Date();
//        System.out.println(date.toString());
//        //以下划线方法，在代码中尽可能不使用
//        System.out.println(date.toGMTString());
//        //yyyy-MM-dd HH:mm:ss
//        System.out.println(date.toLocaleString());
//        System.out.println(date.getYear()+1900);
//        System.out.println(date.getMonth()+1);
//        System.out.println(date.getDate());


        Calendar instance = Calendar.getInstance();
        Calendar instance1 = Calendar.getInstance();
        int y = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int d = instance.get(Calendar.DAY_OF_MONTH);
        int w = instance.get(Calendar.DAY_OF_WEEK);
        int hh = instance.get(Calendar.HOUR_OF_DAY);
        int mm = instance.get(Calendar.MINUTE);
        int ss = instance.get(Calendar.SECOND);
        int ms = instance.get(Calendar.MILLISECOND);
        System.out.println(y+"-"+month+"-"+d+"-"+w+"-"+hh+"-"+mm+"-"+ss+"-"+ms);
        System.out.println(instance.after(instance1));


        Instant instant=Instant.now();
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.toEpochMilli());

    }
}

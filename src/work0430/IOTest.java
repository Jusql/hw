package work0430;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class IOTest {


    public static int fileNum = 0;
    //    public  static String  fileName = "src/work0430/"+fileNum+".txt";
    static ReentrantLock r1 = new ReentrantLock();

    static char randomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 97);
    }

    static void writeFile(String fileName) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            for (int i = 1; i <= 10000; i++) {
                fw.write(randomLetter());
                if (i % 100 == 0) {
                    fw.write('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static HashMap<Character, Integer> readFile(String fileName) {
//        writeFile(fileName);
        HashMap<Character, Integer> resMap = new HashMap<>();
//        FileReader fr = new FileReader(fileName);
//        int data;
//        while ((data=fr.read()) != -1){
//        }
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = bf.readLine())!=null) {
                sb.append(str);
            }

            for (int i = 0;i < sb.length();i++){
                char c = sb.charAt(i);
                Integer value = resMap.get(c);
                if (null==value){
                    resMap.put(c,1);
                }else {
                    resMap.put(c,++value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    static class ReadWriteFile implements Runnable {
        @Override
        public void run() {
            r1.lock();
            writeFile("src/work0430/" + fileNum + ".txt");
            HashMap<Character, Integer> map = readFile("src/work0430/" + fileNum + ".txt");
            System.out.println(fileNum + ".txt文件：" + map);
            fileNum++;
            System.out.println();
            r1.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new ReadWriteFile()).start();
        }
    }
}

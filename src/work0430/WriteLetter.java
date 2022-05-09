package work0430;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WriteLetter {
    /*创建随机a-z的随机数*/
    static char ramdomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 97);
    }

    public WriteLetter() {
        Date d1 = new Date();
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/work0430/c.txt");
            for (int i = 0; i < 10000; i++){

                fw.write(ramdomLetter());
                if(i % 100 ==0){
                    fw.write('\n');
                }
            }
            Date d2 = new Date();
            System.out.println("BufferedOutputStream用时：" + (d2.getTime() - d1.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null){

                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public HashMap<Character,Integer> readFile(String fileName){

        HashMap<Character,Integer> resMap = new HashMap<>();

//        FileReader fr = new FileReader(fileName);
//        int data;
//        while ((data=fr.read()) != -1){
//        }
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str=bf.readLine())!=null){
                sb.append(str);
            }

            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i); //取出字符
                Integer value = resMap.get(c);//根据key得到value
                if (null==value){//map中还没有该key
                    resMap.put(c,1);
                }else {
                    resMap.put(c,++value);//次数加1
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }
    public static void main(String[] args) {
        Map<Character, Integer> characterIntegerMap = new WriteLetter().readFile("src/work0430/c.txt");
        System.out.println(characterIntegerMap);
    }

}

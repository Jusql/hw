package work0430;

import java.io.*;

/**
 * 作业：
 * 开10个线程，每个线程随机往一个文件中随机写入10000字母（10个文件），
 * 然后，统计每个文件中，不同字母的个数。
 * 希望的结果是:Map<字母，数量>
 */
public class Solution {

    class WriteFile extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    File file = new File("src/work0430/" + (i + 1) + ".txt");
//                    FileWriter fw = new FileWriter(file);
//
//                    fw.write((char)Math.random());
                }
            }
        }
    }

    public static void writeFile(String fileName, String content) {
        OutputStream out = null;
        try {
            File file = new File(fileName);
            System.out.println("向文件中写入字母");
            out = new FileOutputStream(file);
            out.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Writer
     */
    public static void testFileWriter(String fileName) {
        FileWriter fw = null;
        try {
            //1.写出到的文件对象
            File file = new File(fileName);
            //2.数据写出 FileWriter对象  可追加
            fw = new FileWriter(file, true);

            //3.写出操作
            fw.write("I can do it!\n");
            fw.write("you can do it too!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//4.流的关闭
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 字节文件复制InputOutputStream
     */
    public static void testFileInputOutputStream(String srcFileName, String destFileName) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcFileName);
            File destFile = new File(destFileName);
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {

                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 测试字符缓冲流
     */
    public static void testBufferedReaderBufferedWriter() {

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("src/work0430/a.txt")));
            bw = new BufferedWriter(new FileWriter(new File("src/work0430/d.txt")));
            //读写操作
            char[] cbuf = new char[1024];
            int len;
            while ((len = br.read(cbuf)) != -1) {
                bw.write(cbuf, 0, len);
                //默认8192 flush
//                bw.flush();
            }
            //方式二：
//            String str;
//            while ((str = br.readLine()) != null){
//                bw.write(str  + '\n');
////                bw.newLine();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件复制
     *
     * @param readFileName
     * @param writeFileName
     */
    public static void testCopyOfFile(String readFileName, String writeFileName) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1.创建File类对象
            File srcFile = new File(readFileName);
            File destFile = new File(writeFileName);

            //2.创建输入流和输出流对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] cbuf = new char[5];
            int len;//每次向cbuf数组中读入len个字符
            while ((len = fr.read(cbuf)) != -1) {
                //每次写出len个字符
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void testFileReader(String fileName) {

//        try {
//            File file = new File(fileName);
//           FileReader fr = new FileReader(file);
//
//            int data;
////            int data = fr.read();
////            while (data != -1) {
//            char[] cbuf = new char[2];
//            while ((data=fr.read(cbuf)) != -1) {
//                System.out.println((char) data);
////                data=fr.read();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fr != null) {
//                try {
//                    fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        FileReader fr = null;
        try {
            File file = new File(fileName);
            //流的实例化
            fr = new FileReader(file);
            char[] cbuf = new char[2];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.println(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         *      File file = new File(fileName);
         *             fr = new FileReader(file);
         *             char[] cbuf = new char[5];
         *             int len;
         *             while((len = fr.read(cbuf)) != -1)
         *             {
         *                 for(int i = 0;i<len;i++)
         *                 {
         *                     sout(cbuf[i]);
         *                 }
         *             }
         *             或
         *             String str = new String(cbuf,0,len);
         *             sout(str);
         *
         *
         */
    }

    public static void writeFileByLetter(String fileName) {
        File file = new File(fileName);


        FileWriter write = null;
        try {
            write = new FileWriter(file);
            write.append("s");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < 1000; i++) {
//            if (i != 0 && i % 10 == 0) {
//                s.append("\n");
//            }
//            s.append(2);
//        }
//
//        Solution.writeFile("src/a.txt", s.toString());
//        System.out.println(new File("src/work0430/a.txt").getAbsolutePath());

        //测试writeChar
//        for (int i = 0; i < 10; i++) {
//            writeFileByLetter("src/work0430/a.txt");
//        }

//        testFileWriter("src/work0430/a.txt");
//        testFileReader("src/a.txt");
//文件复制测试
//        testCopyOfFile("src/work0430/a.txt","src/work0430/b.txt");
//        文件复制 InputOutput测试
//        testFileInputOutputStream("/home/pxy/图片/后半部分（13-24）/23.png", "src/work0430/23.png");
        //字符缓冲流 复制文件测试
        testBufferedReaderBufferedWriter();
    }

}
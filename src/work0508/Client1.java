package work0508;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {
//socket请求ip地址
    private static String host = "127.0.0.1";
//socket请求端口
    private static int port = 8081;
    public static void main(String[] args) {
        try {
            //1.建立一个客户端
            try (Socket socket = new Socket(host, port)) {
                //2.得到socket输出流
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                String sendStr = "发送数据1";
                //发送数据
                printWriter.write(sendStr);
                printWriter.flush();
                socket.shutdownOutput();
                //3.得到socket输入流
                InputStream inputStream = socket.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                while (inputStream.read(bytes) != -1) {
                    sb.append(new String(bytes, "utf-8"));
                }
                System.out.println("接收到的返回数据为：" + sb);
                //4.关闭资源
                printWriter.close();
                outputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            System.err.println("socket请求失败" + e);
        }
    }
}
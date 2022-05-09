package work0508;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {

    private static String host = "127.0.0.1";

    private static int port = 8081;

    public static void main(String[] args) {
        try {

            try (Socket socket = new Socket(host, port)) {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                String sendStr = "发送数据1";

                printWriter.write(sendStr);
                printWriter.flush();
                socket.shutdownOutput();

                InputStream inputStream = socket.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                while (inputStream.read(bytes) != -1) {
                    sb.append(new String(bytes, "utf-8"));
                }
                System.out.println("接收到的返回数据为：" + sb);
                printWriter.close();
                outputStream.close();
                inputStream.close();
//                socket.close();
            }
        } catch (Exception e) {
            System.err.println("socket请求失败" + e);
        }
    }
}
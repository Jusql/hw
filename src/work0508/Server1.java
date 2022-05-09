package work0508;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    // 服务器监听端口
    private static int port = 8081;

    public static void main(String[] args) throws Exception {
        try {

            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("等待客户端请求。。。");
            Socket socket = serverSocket.accept();
            System.out.println("客户端请求来了。。。");

            InputStream inputStream = socket.getInputStream();


            byte[] bytes = new byte[1024];
            int length = 0;
            StringBuilder sb = new StringBuilder();

            while ((length = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, length, "utf-8"));
            }
            System.out.println("接收到的请求数据为：" + sb.toString());

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            String backStr = "服务端接收到了请求";
            printWriter.write(new String(backStr.getBytes(), "utf-8"));
            printWriter.flush();

            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("socket监听失败！" + e);
        }

    }

}

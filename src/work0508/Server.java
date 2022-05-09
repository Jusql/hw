package work0508;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 作业：
 * 1、改写Client和Server程序能够实现Client发送数据给Server,Server也能回发数据给Client
 * <p>
 * 2、写一个Client，一个Server，要求，Client通过控制台输入信息点击回车把信息发送给Server，
 * 然后，Server能将接收到的信息返回给Client,Client打印接收到的信息。
 */
public class Server {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        // 监听指定的端口
        int port = 55533;
        while (true){
        ServerSocket server = new ServerSocket(port);

        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("get message from client: " + sb);
        PrintWriter printWriter = new PrintWriter(outputStream);
//        String strBack;
        String strBack = scanner.next();
        printWriter.write(new String(strBack.getBytes(), "UTF-8"));
        printWriter.flush();

        printWriter.close();
        inputStream.close();
        socket.close();
        server.close();

        }
    }

}

package jedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 0:11 18.5.27
 * @modify by :
 */
public class RedisListener {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6379);
        Socket accept = serverSocket.accept();
        byte[] bytes = new byte[2048];
        accept.getInputStream().read(bytes);
        System.out.println(new String(bytes));

    }

}

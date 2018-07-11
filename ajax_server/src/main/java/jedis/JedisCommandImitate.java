package jedis;

import java.io.IOException;
import java.net.Socket;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 0:28 18.5.27
 * @modify by :
 */
public class JedisCommandImitate {

    private Socket socket;

    public JedisCommandImitate() throws IOException {
        Socket socket = new Socket("127.0.0.1" , 6379);
    }

    public void imitateSetCommand() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("*3");
        sb.append("\r\n");
        sb.append("$3");
        sb.append("\r\n");
        sb.append("SET");
        sb.append("\r\n");
        sb.append("$4");
        sb.append("\r\n");
        sb.append("name");
        sb.append("\r\n");
        sb.append("$8");
        sb.append("\r\n");
        sb.append("lvweihao");

        socket.getOutputStream().write(sb.toString().getBytes());
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);

        System.out.println(new String(bytes));

    }

    public static void main(String[] args) throws IOException {

        JedisCommandImitate jedisCommandImitate = new JedisCommandImitate();
        jedisCommandImitate.imitateSetCommand();

    }

}

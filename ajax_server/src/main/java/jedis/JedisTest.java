package jedis;

import redis.clients.jedis.Jedis;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 23:55 18.5.26
 * @modify by :
 */
public class JedisTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("name" , "lvweihao");
//        String name = jedis.get("name");
//        System.out.println(name);

    }

}

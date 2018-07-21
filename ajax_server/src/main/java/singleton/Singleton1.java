package singleton;

/**
 * @author :lvweihao@outlook.com
 * @description: 通过类加载机制实现的饿汉式单例
 * @date :create in 22:05 2018/7/16
 * @modify by :
 */
public class Singleton1 {

    private static final Singleton1 singleton = new Singleton1();

    private Singleton1() {

    }

    private static Singleton1 getSingleton() {
        return singleton;
    }

}

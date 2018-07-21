package singleton;

/**
 * @author :lvweihao@outlook.com
 * @description: 以静态类形式实现的懒汉式单例
 * @date :create in 22:07 2018/7/16
 * @modify by :
 */
public class Singleton2 {

    private static class SingletonHolder {

        private static final Singleton2 SINGLETON = new Singleton2();

    }

    private Singleton2() {

    }

    private static final Singleton2 getSingleton() {
        return SingletonHolder.SINGLETON;
    }

}

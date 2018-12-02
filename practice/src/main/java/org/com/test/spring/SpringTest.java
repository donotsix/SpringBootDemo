package org.com.test.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SpringTest {

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, IllegalArgumentException, InvocationTargetException {
        // 模拟spring注册bean
        Map<String, Object> map = new HashMap<>();
        map.put("actionBean", "org.com.test.spring.PersonActionImpl");
        // 模拟spring获取bean
        String beanName = (String) map.get("actionBean");
        Class<?> cls = Class.forName(beanName);
        Object object = cls.newInstance();
        // 模拟spring依赖注入，set方法
        ActionBean action = new ActionBean();
        Method method = action.getClass().getMethod("setPersonAction", PersonAction.class);
        method.invoke(action, object);
        // 模拟bean方法调用
        action.say();

    }
}

package standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiConsumer;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 0:21 18.6.7
 * @modify by :
 */
public class StandardTest {

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();
        map.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String s, Object o) {

            }
        });


        ArrayList<Long> list = new ArrayList<>();
        // 去重
        HashSet<Long> set = new HashSet<>(list);
        // 恢复为list
        ArrayList<Long> list1 = new ArrayList<>(set);

    }

}

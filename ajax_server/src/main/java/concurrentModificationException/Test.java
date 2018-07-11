package concurrentModificationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 21:46 18.6.19
 * @modify by :
 */
public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // 出现ConcurrentModificationException
        /*for (Integer i : list) {
            if (1 == i) {
                list.remove(i);
            }
        }*/

        // 使用listiterator遍历 , 遍历中删除集合元素 , 未出现ConcurrentModificationException
        /*ListIterator<Integer> listIterator = list.listIterator();
        if (listIterator.hasNext()) {
            Integer next = listIterator.next();
            if (1 == next) {
                list.remove(next);
            }
        }*/

        // 使用listiterator遍历 , 遍历中增加集合元素 , 未出现ConcurrentModificationException
        /*ListIterator<Integer> listIterator = list.listIterator();
        if (listIterator.hasNext()) {
            Integer next = listIterator.next();
            if (1 == next) {
                list.add(5);
            }
        }*/

        // 使用iterator遍历 , 遍历中增加元素, 未出现并发修改异常
        /*Iterator<Integer> iterator = list.iterator();
        if (iterator.hasNext()) {
            Integer next = iterator.next();
            if (1 == next) {
                list.add(5);
            }
        }*/

        // 使用iterator遍历 , 遍历中删除元素, 未出现并发修改异常
        /*Iterator<Integer> iterator = list.iterator();
        if (iterator.hasNext()) {
            Integer next = iterator.next();
            if (1 == next) {
                list.remove(next);
            }
        }*/

        Iterator<Integer> iterator = list.iterator();
        if (iterator.hasNext()) {
            Integer next = iterator.next();
            if (1 == next) {
                iterator.remove();
            }
        }

        System.out.println(list);
    }

}

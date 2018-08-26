package guava.collections;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.stream.Stream;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 12:04 2018/7/22
 * @modify by :
 */
public class CollectionsTest {

    public static void main(String[] args) {

        HashMap<String, Map<String , String>> map = newHashMap();

        ArrayList<String> list = newArrayList();

        HashSet<String> set = newHashSet();

        ImmutableList<String> immutableList = of("吕伟豪", "宁秦洁");

        ArrayList<String> arrayList = newArrayList("吕伟豪", "宁秦洁");


        int compare = Ints.compare(1, 2);
        System.out.println(compare);

        int[] array = Ints.toArray(of(1, 2));

        String retain = CharMatcher.DIGIT.retainFrom("some text 893 and more]");
        System.out.println(retain);

        String remove = CharMatcher.DIGIT.removeFrom("some text 893 and more]");
        System.out.println(remove);

        String[] subdirs = { "usr", "local", "lib"};

        String directory = Joiner.on("/").join(subdirs);
        System.out.println(directory);
        Iterable<String> strings = Splitter.on("/").split(directory);
        strings.forEach(s -> System.out.println(s));

        int[] numbers = { 1, 2, 3, 4, 5};
        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
        System.out.println(numbersAsString);
        Iterable<String> stringIterable = Splitter.on(",").split(numbersAsString);
        stringIterable.forEach(s -> System.out.println(s));

        String testString = "foo , what,,,more,";
        // 简单处理会留出空白
        String[] splitRegular = testString.split(",");
        System.out.println(splitRegular);
        Stream.of(splitRegular).forEach(s -> System.out.println(s));
        System.out.println(splitRegular);
        List<String> splitToList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(testString);
        splitToList.forEach(s -> System.out.println(s));

        String str = "key1: 1; key2: 2  ; key3: 3";
        Map<String, String> m = Splitter.on(';')
                .omitEmptyStrings()
                .trimResults()
                .withKeyValueSeparator(":")
                .split(str);

        m.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + "=" + entry.getValue());
        });


        ArrayList<String> strArr1 = Lists.newArrayList(

                "test1","test2","test3",null,"test4",null,null);


        String join1 = Joiner.on(';').skipNulls().join(strArr1);
        String join2 = Joiner.on(';').useForNull("_").join(strArr1);
        System.out.println(join1);
        System.out.println(join2);


        Map<String, String> map2 = Maps.newHashMap();
        map2.put("key1", "value1");
        map2.put("key2", "value2");
        map2.put("key3", null);
        map2.put("key4", "value3");
        String mapString = Joiner.on(';').useForNull("NULL").withKeyValueSeparator("=").join(map2);
        System.out.println(mapString);


        ArrayList<String> strArr = Lists.newArrayList(" test1","test2 "," test3 ",null,"test4",null,null,"", "  ");

        Predicate<String> EMPTY_OR_NULL_FILTER = new Predicate<String>() {
            @Override
            public boolean apply(String str){
                str = Strings.nullToEmpty(str).trim();
                return !Strings.isNullOrEmpty(str);

            }
        };

        Function<String, String> TRIM_RESULT = new Function<String, String>(){
            @Override
            public String apply(String str){
                return Strings.nullToEmpty(str).trim();
            }
        };

        Collection<String> collection1 = filter(strArr, EMPTY_OR_NULL_FILTER);
        Collection<String> collection2 = Collections2.transform(collection1, TRIM_RESULT);
        System.out.println(collection1);
        System.out.println(collection2);
        String joinStr = Joiner.on(';').skipNulls().join(Collections2.transform(filter(strArr, EMPTY_OR_NULL_FILTER), TRIM_RESULT));
        System.out.println(joinStr);



        String tmpValue = "a_b_c_1_2_3";
        String[] valArr = tmpValue.split("_");

        // 求字符串数组的子串，并最后拼接起来
        String tmpVal = "";
        for (int i = 1; i < valArr.length; i++) {
            tmpVal = tmpVal.equalsIgnoreCase("") ? valArr[i] : tmpVal + "_"
                    + valArr[i];
        }
        System.out.println(tmpVal);
        System.out.println("———————");

        // 上面这么一段与下面这句等价
        System.out.println(Joiner.on("_").join(Lists.newArrayList(valArr).subList(1, valArr.length)));


        int[] array1 = { 1, 2, 3, 4, 5};
        int a = 4;
        boolean hasA = false;
        for (int i : array1) {
            if (i == a) {
                hasA = true;
            }
        }
        System.out.println(hasA);
        System.out.println(Ints.contains(array1, a));
        System.out.println(Ints.indexOf(array1, a));
        System.out.println(Ints.max(array1));
        System.out.println(Ints.min(array1));
        System.out.println(Ints.asList(Ints.concat(array1, new int[]{1, 3})));


        List<String> names = Lists.newArrayList("Aleksander", "Jaran", "Integrasco", "Guava", "Java");
        Collection<String> filtered = filter(names, or(or(equalTo("Aleksander"), equalTo("Jaran")), lessThan()));

        System.out.println(filtered);


        //
        HashSet<Integer> setA = newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = newHashSet(4, 5, 6, 7, 8);

        // 并集
        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Integer integer : union)
            System.out.println(integer);

        // in A not in B
        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("difference:");
        for (Integer integer : difference)
            System.out.println(integer);

        // 交集
        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:");
        for (Integer integer : intersection)
            System.out.println(integer);
    }

    private static Predicate lessThan() {
        return new ShortThanLength();
    }

    private static class ShortThanLength implements Predicate<String>{
        @Override
        public boolean apply(String t) {
            return t != null && t.length() <= 4;
        }
    }
}

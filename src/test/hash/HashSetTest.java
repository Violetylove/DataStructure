package test.hash;

import adt.Set;
import hash.HashSet;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class HashSetTest {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        System.out.println(set);

        System.out.println("所删元素是：" + set.remove(2));

        System.out.println(set);
    }
}

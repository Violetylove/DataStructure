package test.linked.list;

import linked.list.singly.SinglyList;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SinglyListTest {
    public static void main(String[] args) {
        SinglyList<Integer> list = new SinglyList<>();
        list.insert(2);
        list.insert(-1);
        list.insert(3);
        System.out.println(list);
        System.out.println("链表长度为："+list.size());

        System.out.println("所删元素为："+list.removeByValue(2));
        System.out.println("所删元素为："+list.removeByValue(-1));
        System.out.println("所删元素为："+list.removeByValue(3));

        System.out.println();
        System.out.println(list);
        System.out.println("链表长度为："+list.size());
    }
}

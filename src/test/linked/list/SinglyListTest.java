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
        list.insert(-1,1);
        list.insert(2,3);
        System.out.println(list);
        System.out.println("链表长度为："+list.size());

        //System.out.println("所查元素为："+list.search(3));
        //System.out.println("删除的元素为："+x);
        System.out.println();
        System.out.println(list);
        System.out.println("获取的元素为："+list.get(20));
        System.out.println("链表长度为："+list.size());
    }
}

package test.linked.list;

import linked.list.doubly.CirDoublyList;
import linked.list.doubly.DoubleNode;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class DoublyTest {
    public static void main(String[] args) {
        DoubleNode<Integer> node1 = new DoubleNode<>(1);
        DoubleNode<Integer> node2 = new DoubleNode<>(2);
        DoubleNode<Integer> node3 = new DoubleNode<>(3);
        DoubleNode<Integer> node4 = new DoubleNode<>(4);

        CirDoublyList<Integer> list1 = new CirDoublyList<>();
        CirDoublyList<Integer> list2 = new CirDoublyList<>();

        list1.insert(node1);
        list1.insert(node2);
        list1.insert(node3);
        list2.insert(node4);
        list2.insert(5);
        list2.insert(6);

        System.out.println("链表list1为：");
        list1.showAll();
        System.out.println("链表list1长度为：" + list1.size());

        System.out.println();
        System.out.println("链表list2为：");
        list2.showAll();
        System.out.println("链表list2长度为：" + list2.size());


        list2.append(list1);

        System.out.println();
        System.out.println("修改后的链表list2为：");
        list2.showAll();
        System.out.println("链表list2长度为：" + list2.size());
    }
}

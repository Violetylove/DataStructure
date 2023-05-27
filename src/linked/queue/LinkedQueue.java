package linked.queue;

import adt.Queue;
import linked.list.singly.Node;

/**
 * 单链表实现的队列
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public final class LinkedQueue<T> implements Queue<T> {

    /*
    头指针，指向队首
     */
    private Node<T> front;
    /*
    尾指针，指向队尾
     */
    private Node<T> rear;

    /**
     * 构造空队列
     */
    public LinkedQueue() {
        front = rear = null;
    }

    public boolean isEmpty(){
        return front == null && rear == null;
    }

    /**
     * 元素x入队
     * @param value 待入队元素
     * @return true为入队成功，false为入队失败
     */
    @Override
    public boolean add(T value) {
        if (value == null)
            return false;
        Node<T> q = new Node<>(value, null);
        // 空队插入
        if (front == null)
            front = q;
        // 尾插入
        else
            rear.next = q;
        rear = q;
        return true;
    }

    /**
     * 返回队首元素
     * @return 队首元素
     */
    @Override
    public T peek() {
        return isEmpty() ? null : front.data;
    }

    /**
     * 元素出队列
     * @return 出队列的元素
     */
    @Override
    public T poll() {
        if (isEmpty())
            return null;
        T x = front.data;
        front = front.next;
        if (front == null)
            rear = null;
        return x;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "{");
        for (Node<T> p = front; p != null; p=p.next)
            str.append(p.data.toString())
                    .append(p.next != null ? ", " : "");
        return str + "}";
    }

    public void showAll(){
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        Node<T> temp = front;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

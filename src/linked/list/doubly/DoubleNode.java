package linked.list.doubly;

/**
 * 双向结点类
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class DoubleNode <T>{

    /*
    数据域
     */
    public T data;
    /*
    两个指针，分别指向前驱和后继结点
     */
    public DoubleNode<T> prev,next;

    public DoubleNode() {
    }

    public DoubleNode(T data) {
        this.data = data;
        this.prev = this;
        this.next = this;
    }

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String  toString() {
        return "DoubleNode{" +
                "data=" + data +
                '}';
    }
}

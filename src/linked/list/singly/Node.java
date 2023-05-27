package linked.list.singly;

/**
 * 结点类
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class Node<T>{

    public T data;
    public Node<T> next;

    public Node() {
        this(null, null);
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

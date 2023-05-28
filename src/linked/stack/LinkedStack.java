package linked.stack;

import adt.Stack;
import linked.list.singly.Node;
import linked.list.singly.SinglyList;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public final class LinkedStack<T> implements Stack<T> {

    private final SinglyList<T> list;

    public LinkedStack(){
        list = new SinglyList<>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T value) {
        list.insert(0, value);
    }

    @Override
    public T pop() {
        return list.removeByIndex(0);
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "{");
        for (Node<T> p = list.getHead().next; p != null; p=p.next)
            str.append(p.data.toString())
                    .append(p.next != null ? ", " : "");
        return str + "}";
    }
}

package linked.list.singly;

/**
 * 单链表类，注释不写，谁看不懂谁是小笨蛋。
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SinglyList<T> {

    private Node<T> head;

    public SinglyList(){
        head = new Node<>();
    }

    public Node<T> getHead(){
        return head;
    }

    public boolean isEmpty(){
        return head.next == null;
    }

    public int size(){
        Node<T> target = head.next;
        int count = 0;
        while (target != null){
            count ++;
            target = target.next;
        }
        return count;
    }

    public boolean insert(int index, T value){
        if (value == null)
            return false;
        Node<T> front = head;
        for (int j=0; front.next != null && j < index; j++)
            front = front.next;
        front.next = new Node<>(value, front.next);
        return true;
    }

    public boolean insert(T value){
        return insert(Integer.MAX_VALUE, value);
    }

    public boolean set(int index, T value){
        if (value == null || isEmpty())
            return false;
        Node<T> target = head.next;
        for (int j=0; target.next != null && j < index; j++)
            target = target.next;
        target.data = value;
        return true;
    }

    public T get(int index){
        if (isEmpty()) return null;
        Node<T> target = head.next;
        for (int i = 0; target.next != null && i < index; i++) {
            target = target.next;
        }
        return target.data;
    }

    public T search(int index){
        if (isEmpty())
            return null;
        Node<T> target = head.next;
        for (int i = 0; target.next != null && i < index; i++)
            target = target.next;
        return target.data;
    }

    public Node<T> search(T key){
        if (isEmpty())
            return null;
        Node<T> target = head.next;
        while (target != null){
            if (target.data == key)
                return target;
            target = target.next;
        }
        return null;
    }

    /**
     * 删除index位置的结点
     * @param index 索引位置。对index容错，真不容易啊
     * @return 删除的结点
     */
    public T remove(int index) {
        if (isEmpty()) return null;
        Node<T> front = head;
        Node<T> temp = head; // 指向尾结点的前一个结点
        // 找结点
        for (int i = 0; front.next != null && i < index; i++) {
            front = front.next;
            if (front.next != null)
                temp = front;
        }
        T x;
        // 头删除和中间删除
        if (front.next != null){
            x = front.next.data;
            front.next = front.next.next;
        // 尾删除
        }else {
            x = temp.next.data;
            temp.next = null;
        }
        return x;
    }

    public void clear(){
        head.next = null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "{");
        for (Node<T> p = head.next; p != null; p=p.next)
            str.append(p.data.toString())
                    .append(p.next != null ? ", " : "");
        return str + "}";
    }
}
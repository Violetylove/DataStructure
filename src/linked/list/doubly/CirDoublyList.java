package linked.list.doubly;

/**
 * 循环双链表类
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class CirDoublyList<T>{

    /*
    头指针
     */
    private DoubleNode<T> head;

    /**
     * 构造空链表
     */
    public CirDoublyList(){
        head = new DoubleNode<>();
        head.prev = head;
        head.next = head;
    }

    /**
     * 构造指定非空头结点的循环双链表
     * @param doubleNode 循环双链表的非空头结点
     */
    public CirDoublyList(DoubleNode<T> doubleNode){
        head = new DoubleNode<>();
        // 先修改新头结点的前驱和后继。
        head.prev = doubleNode.prev;
        head.next = doubleNode;
        // 再修改原尾结点的后继。原尾结点(doubleNode.prev)的后继是原头结点(doubleNode)，将后继改为新头结点(head)
        doubleNode.prev.next = head;
        // 最后修改原头结点的前驱为新头结点。
        doubleNode.prev = head;
    }

    public DoubleNode<T> getHead(){
        return this.head;
    }

    public boolean isEmpty(){
        return head.next == head;
    }

    /**
     * 在index位置插入数据data为x的结点
     * @param index 插入的位置
     * @param x 插入的数据data
     * @return 插入成功返回true，插入失败则返回false
     */
    public boolean insert(int index, T x){
        if (x == null) return false;
        // front用来寻找i位置前一个结点或尾结点
        DoubleNode<T> front = head;
        for (int j = 0; front.next !=head && j < index; j++) {
            front = front.next;
        }
        // 对i容错，包括头插入(index<=0)，中间/尾插入(index>0)
        DoubleNode<T> q = new DoubleNode<>(x, front, front.next);
        front.next.prev = q;
        front.next = q;
        return true;
    }

    /**
     * 尾部插入数据data为x的结点
     * @param x 插入的数据data
     * @return 插入成功返回true，插入失败则返回false
     */
    public boolean insert(T x){
        if (x == null) return false;
        DoubleNode<T> q = new DoubleNode<>(x, head.prev, head);
        head.prev.next = q;
        head.prev = q;
        return true;
    }

    /**
     * 在index位置插入结点
     * @param index 索引位置
     * @param q 待插入的结点
     * @return 插入成功返回true，插入失败则返回false
     */
    public boolean insert(int index,DoubleNode<T> q){
        if (q == null) return false;
        // front用来寻找i位置前一个结点或尾结点
        DoubleNode<T> front = head;
        for (int j = 0; front.next !=head && j < index; j++) {
            front = front.next;
        }
        // 对i容错，包括头插入(index<=0)，中间/尾插入(index>0)
        q.prev = front;
        q.next = front.next;
        front.next.prev = q;
        front.next = q;
        return true;
    }

    /**
     * 尾插入结点
     * @param q 待插入节点
     * @return 插入成功返回true，插入失败则返回false
     */
    public boolean insert(DoubleNode<T> q){
        if (q == null)
            return false;
        q.prev = head.prev;
        q.next = head;
        head.prev.next = q;
        head.prev = q;
        return true;
    }

    /**
     * 拼接链表。将一个链表的置空头结点剥离，拼接剩余的结点。
     * @param list 待拼接的链表
     * @return 拼接后的链表
     */
    public CirDoublyList<T> append(CirDoublyList<T> list){
        if (list.isEmpty()) {
            return this;
        } else if (list == this) {
            System.out.println("不能拼接链表本身");
            return this;
        } else {
            // 为了方便和避免引用错误，引入front指向list的第一个非空结点,rear指向list的尾结点。
            DoubleNode<T> front = list.getHead().next;
            DoubleNode<T> rear = list.getHead().prev;

            // 剥离list的头结点!!!
            list.getHead().prev = list.getHead().next = list.getHead();

            // 先修改list的结点指针
            // list的第一个非空结点(front)的前驱改为this的尾结点(this.head.prev)
            front.prev = this.head.prev;
            // list的尾结点(rear)的后继改为this的头结点(this.head)
            rear.next = this.head;

            // 再修改this的结点指针
            // this的尾结点(this.head.prev)的后继修改为list的第一个非空结点(front)
            (this.head.prev).next = front;
            // this的头结点(this.head)的前驱修改为list的尾结点
            this.head.prev = rear;

            return this;
        }
    }

    /**
     * 查看index位置上的结点
     * @param index 索引位置。index<0时返回第一个结点，超出索引范围时返回尾结点
     * @return index位置上的结点
     */
    public DoubleNode<T> search(int index){
        DoubleNode<T> target = head.next;
        for (int j = 1; target.next != head && j <= index; j++) {
            target = target.next;
        }
        return target;
    }

    /**
     * 获取链表有效长度
     * @return 有效长度
     */
    public int size(){
        DoubleNode<T> temp = head.next;
        int count = 0;
        while (temp != head){
            count ++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 删除index位置的结点.对index容错，不再赘述。
     * @param index 待删结点的索引
     * @return 被删除的结点
     */
    public DoubleNode<T> remove(int index){
        // front为目标结点的前一结点
        DoubleNode<T> front = head;
        for (int j = 0; front.next !=head && j < index; j++) {
            front = front.next;
        }
        DoubleNode<T> target; // 待删除的目标结点
        if (front.next == head) // 已经遍历了所有结点，待删结点为尾结点。
            target = front;
        else
            target = front.next;
        // 对 index 容错，删除第一个结点(index<0)，删除尾结点(index>0)
        target.next.prev = target.prev;
        target.prev.next = target.next;
        // 剥离它！！！
        target.prev = target.next = target;
        return target;
    }

    /**
     * 清空链表
     */
    public void clear(){
        head.prev = head.next = head;
    }

    /**
     * 显示所有结点
     */
    public void showAll(){
        if (isEmpty()) System.out.println("链表为空。。。");
        DoubleNode<T> temp = head.next;
        while (temp != head){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

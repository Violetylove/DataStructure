package sequential.queue;

import adt.Queue;

/**
 * 顺序队列
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public final class SeqQueue<T> implements Queue<T> {

    /*
     存储队列数据元素的数组
     */
    private Object[] element;
    /*
    队列头指针
     */
    private int front;
    /*
    队列尾指针，指向尾元素后一个位置，是空位置。
     */
    private int rear;
    /*
    最小容量
     */
    private static final int MIN_CAPACITY = 16;

    public SeqQueue(int length){
        // 设置数组最小容量
        if (length < MIN_CAPACITY)
            length = MIN_CAPACITY;
        element = new Object[length];
        front = rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull(){
        return (rear + 1) % element.length == front;
    }

    @Override
    public boolean add(T value) {
        if (value == null)
            return false;
        if (isFull()) { // 队列为满，扩充容量。默认扩充为原容量的2倍
            Object[] temp = element;
            element = new Object[temp.length * 2];
            int j = 0;
            for (int i = front; i != rear; i = (i + 1) % temp.length)
                element[j++] = temp[i];
            front = 0;
            rear = j;
        } // 入队
        element[rear] = value;
        rear = (rear + 1) % element.length;
        return true;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : (T) element[front];
    }

    @Override
    public T poll() {
        if (isEmpty())
            return null;
        T target = (T)element[front];
        front = (front + 1) % element.length;
        return target;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "{");
        for (int i = front; i != rear; i = (i + 1) % element.length)
            str.append(element[front].toString())
                    .append(i == rear-1 ? "" : ", ");
        return str + "}";
    }
}

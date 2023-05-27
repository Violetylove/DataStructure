package adt;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public interface Queue<T> {

    /**
     * 判断队列是否为空
     * @return true为空，false为非空
     */
    public boolean isEmpty();

    /**
     * 入队
     * @return true为添加成功，false为添加失败
     */
    public boolean add(T value);

    /**
     * 查看队首元素，不是删除。
     * @return 队首元素，若为空则返回null
     */
    public T peek();

    /**
     * 出队
     * @return 出队元素
     */
    public T poll();
}

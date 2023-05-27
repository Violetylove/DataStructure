package adt;

/**
 * 栈接口
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public interface Stack<T> {
    /**
     * 判断栈是否空
     * @return true为空，false为非空
     */
    public boolean isEmpty();

    /**
     * 元素x入栈
     * @param value 入栈的数据
     */
    public void push(T value);

    /**
     * 出栈
     * @return 栈顶元素
     */
    public T pop();

    /**
     * 返回栈顶元素，未出栈
     * @return 返回栈顶元素
     */
    public T peek();
}

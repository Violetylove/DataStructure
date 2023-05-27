package sequential.stack;

import adt.Stack;
import sequential.list.SeqList;

/**
 * 顺序栈
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public final class SeqStack<T> implements Stack<T> {

    private final SeqList<T> list;

    /**
     * 执行顺序表构造方法
     * @param length 栈容量
     */
    public SeqStack(int length){
        list = new SeqList<>(length);
    }

    /**
     * 构造空栈
     */
    public SeqStack(){
        this(16);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T value) {
        list.insert(value);
    }

    @Override
    public T pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public T peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "{");
        for (int i = 0; i < list.size(); i++ )
            str.append(list.get(i).toString())
                    .append(i == list.size()-1 ? "" : ", ");
        return str + "}";
    }
}

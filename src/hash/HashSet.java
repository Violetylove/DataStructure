package hash;

import linked.list.singly.Node;
import linked.list.singly.SinglyList;

/**
 * 散列集合类
 * @author Winter Yuan
 * @version 1.0
 */
public class HashSet <T>{
    // 散列表，同义词单链表对象数组
    private SinglyList<T>[] table;
    // 元素个数
    private int count = 0;
    // 装填因子
    public static final float LOAD_FACTOR = 0.75f;
    // 初始容量，默认为16
    private static final int CAPACITY = 1 << 4;

    /**
     * 构造初始容量的散列表
     * @param length 初始容量
     */
    public HashSet(int length){
        if (length < 10)
            length = 10;
        table = new SinglyList[length];
        for (int i = 0; i < table.length; i++)
            table[i] = new SinglyList<>();
    }

    /**
     * 构造空散列表，默认容量
     */
    public HashSet(){
        this(CAPACITY);
    }

    /**
     * 构造散列表，由values提供元素集合
     * @param values 元素集合
     */
    public HashSet(T[] values) {
        this((int) (values.length / HashSet.LOAD_FACTOR));
        for (int i = 0; i < values.length; i++)
            add(values[i]);
    }

    /**
     * 散列函数
     * @param value 待计算的值
     * @return 散列值，即hash值
     */
    private int hash(T value){
        int key = Math.abs(value.hashCode());
        // 除留余数法，除数是散列表容量
        return key % table.length;
    }

    public T search(T key){
        Node<T> find = table[hash(key)].search(key);
        return find == null ? null : find.data;
    }

    public boolean add(T value){
        if (value == null || search(value) != null)
            return false;
        // 散列表满则扩容,这是散列表的满否判断
        if (count >= table.length*LOAD_FACTOR){

        }
    }

}

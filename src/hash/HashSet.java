package hash;

import adt.Set;
import linked.list.singly.Node;
import linked.list.singly.SinglyList;

/**
 * 散列集合类
 * @author Winter Yuan
 * @version 1.0
 */
public class HashSet <T> implements Set<T> {
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

    @Override
    public T search(T key){
        Node<T> find = table[hash(key)].searchByValue(key);
        return find == null ? null : find.data;
    }

    /**
     * 散列表的插入方法
     * @param value 待插入值
     * @return 是否成功插入
     */
    @Override
    public boolean add(T value){
        if (value == null || search(value) != null)
            return false;
        // 散列表满则扩容,这是散列表的满否判断
        // 如果满，构造扩容了的空散列表
        if (count >= table.length*LOAD_FACTOR){
            SinglyList<T>[] temp = table;
            table = new SinglyList[table.length * 2];
            for (int i = 0; i < table.length; i++)
                table[i] = new SinglyList<>();
            count = 0;
            // 往空散列表装入数据
            for (int i = 0; i < temp.length; i++)
                for (Node<T> p = temp[i].getHead().next; p != null; p = p.next)
                    // 添加元素，递归调用自己
                    add(p.data);
        }
        count++;
        // 太骚了！！！
        return table[hash(value)].insert(0,value);
    }

    /**
     * 删除散列表中指定value的元素
     * @param value 指定的值
     * @return 删除了的元素
     */
    @Override
    public T remove(T value){
        T target = table[hash(value)].removeByValue(value);
        if (target != null)
            count--;
        return target;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()+"{\n");
        for (SinglyList<T> list : table)
            str.append(list.isEmpty() ? "" : list+ "\n");
        return str + "}";
    }
}
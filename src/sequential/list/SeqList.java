package sequential.list;

/**
 * 顺序表
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SeqList<T> {

    // 顺序表元素个数(长度)
    protected int n;
    // 用到的数组，存储数据元素
    protected Object[] element;
    // 最小容量
    private static final int MIN_CAPACITY = 16;

    public SeqList(int length) {
        if (length < MIN_CAPACITY)
            length = MIN_CAPACITY;
        element = new Object[length];
        n = 0;
    }

    public SeqList(){
        this(MIN_CAPACITY);
    }

    /**
     * 构造顺序表，由数组values提供元素
     * @param values 要装入顺序表里的数组。
     */
    public SeqList(T[] values){
        // 创建2倍values数组容量的空表
        this(values.length * 2);
        // 复制非空元素
        for (int i = 0; i < values.length; i++)
            if (values[i] != null)
                element[n++] = values[i];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    /**
     * 查找数组中首个与value相等的元素的下标。
     * @param value 查找的数据
     * @return 下标。-1表示未找到。
     */
    public int search(T value){
        for (int i = 0; i < n; i++)
            if (value.equals(element[i]))
                return i;
        return -1;
    }

    public T get(int index){
        if (index >= 0 && index < n){
            return (T) element[index];
        }
        return null;
    }

    /**
     * 修改指定位置的元素
     * @param index 修改位置。超出索引范围会抛出序号越界异常
     * @param value 修改后的值。为空会抛出空指针异常
     */
    public void set(int index, T value){
        if (value == null)
            throw new NullPointerException(" x==null");
        if (index >= 0 && index < n)
            element[index] = value;
        else
            throw new IndexOutOfBoundsException(value + "");
    }

    /**
     * 指定位置插入。对index容错
     * @param index 插入位置
     * @param value 插入的数据
     * @return true为插入成功，false为插入失败
     */
    public boolean insert(int index, T value){
        if (value == null)
            return false;
        if (index < 0)
            index = 0;
        if (index > n)
            index = n;
        if (n == element.length){ // 容量满了，扩充
            // 用到的中介数组
            Object[] source = element;
            element = new Object[source.length * 2]; // 扩充为原数组容量的2倍
            for (int i = 0; i < index; i++)
                element[i] = source[i];
        }
        for (int i = n-1 ; i >= index; i++)
            element[i+1] = element[i];
        element[index] = value;
        n++;
        return true;
    }

    /**
     * 尾插入
     * @param value 待插入的数据
     * @return true为插入成功，false为插入失败
     */
    public boolean insert(T value){
        return insert(n, value);
    }

    /**
     * 删除下标为index的元素
     * @param index 指定的下标
     * @return 删除的数据
     */
    public T remove(int index){
        if ( index >= 0 && index < n){
            T target = (T)element[index];
            for (int i = index; i < n - 1; i++)
                element[i]  = element[i + 1];
            element[n - 1] = null;
            n--;
            return target;
        }
        return null;
    }

    /**
     * 删除表中首个与value相等的元素
     * @param value 指定的元素
     * @return 被删元素
     */
    public T remove(T value){
        for (int i = 0; i < n; i++)
            if (value.equals(element[i])){
                T target = ((T) element[i]);
                for (int j = i; j < n - 1; j++)
                    element[j] = element[j + 1];
                element[n - 1] = null;
                n--;
                return target;
            }
        return null;
    }

    public void clear(){
        n = 0; // 长度n设置为0，但未释放数组空间。
    }

    @Override
    public String toString() {
        String str = this.getClass().getSimpleName() + "{";
        if (n > 0)
            str += element[0].toString();
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = 0; i < n; i++)
            strBuilder.append(", ").append(element[i].toString());
        str = strBuilder.toString();
        return str + "}";
    }
}

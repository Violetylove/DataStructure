package application.search;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class SearchAlgorithm {

    private SearchAlgorithm() {
    }

    /**
     * 线性查找
     * @param array 数组
     * @param value 待查找的值
     * @return 元素下标；-1表示没有找到。
     */
    public static int SeqSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return i;
        }
        return -1;
    }

    /**
     * 二分查找接口样式方法
     * @param array 排序数组
     * @param value 待查找的值
     * @return 元素下标；-1表示没有找到。
     */
    public static int BinarySearch(int[] array, int value) {
        return BinarySearch(array, 0, array.length - 1, value);
    }

    /**
     * 二分查找实现
     * @param array 待排序数组
     * @param begin 子序列首下标
     * @param end 子序列尾下标
     * @param value 待查找值
     * @return 元素下标；-1表示没有找到。
     */
    private static int BinarySearch(int[] array, int begin, int end, int value) {
        // 数组中没有value这个数，返回-1。
        if (begin > end || value < array[begin] || value > array[end])
            return -1;
        // 中间值的下标
        int mid = (begin + end) / 2;
        if (value < array[mid])
            return BinarySearch(array, begin, mid - 1, value);
        else if (value > array[mid])
            return BinarySearch(array, mid + 1, end, value);
        else
            return mid;
    }

    /**
     * 插值查找实现
     * @param array 待排序数组
     * @param value 待查找值
     * @return 元素下标；-1表示没有找到。
     */
    public static int InsertionValueSearch(int[] array, int value){
        return InsertionValueSearch(array, 0, array.length - 1, value);
    }

    /**
     * 插值查找实现
     * <p>插值查找，是二分查找的优化。二分查找中间值下标选取的是子序列中间值下标，即
     * <blockquote><pre>
     * int mid = begin + 1/2 * (end - begin)
     * </pre></blockquote>
     * 而二分查找的中间值下标为：
     * <blockquote>
     * <pre>
     * int mid = begin + (end - begin) * (value - array[begin]) / (array[end] - value)
     * </pre></blockquote>
     * </p>
     * @param array 待排序数组
     * @param begin 子序列首下标
     * @param end 子序列尾下标
     * @param value 待查找值
     * @return 元素下标；-1表示没有找到。
     */
    private static int InsertionValueSearch(int[] array, int begin, int end, int value){
        // 数组中没有value这个数，返回-1。
        if (begin > end || value < array[begin] || value > array[end])
            return -1;
        // 修改二分查找的中间值的选取策略
        int mid = begin + (end - begin) * (value - array[begin]) / (array[end] - value);
        // 其余都与二分查找一致
        if (value < array[mid])
            return InsertionValueSearch(array, begin, mid - 1, value);
        else if (value > array[mid])
            return InsertionValueSearch(array, mid + 1, end, value);
        else
            return mid;
    }
}

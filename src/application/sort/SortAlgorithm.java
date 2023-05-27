package application.sort;

/**
 * 冒泡排序
 * @author Winter Yuan
 * @version 1.0
 */
public class SortAlgorithm {

    private SortAlgorithm(){}

    /**
     * 冒泡排序
     * @param array 欲排序数组
     */
    public static void BubbleSort(int[] array){
        int temp;
        boolean swap; // 用来优化算法，标记某次遍历是否交换了。当未发生就交换应该直接结束算法。
        for (int i = 0; i < array.length - 1; i++) {
            swap = false;
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swap = true; // 交换就为true
                }
            }
            if (!swap) // 未发生交换。
                break;
        }
    }

    /**
     * 选择排序
     * <p>每次寻找剩余元素最小值，让其与首个未排序元素交换位置。
     * @param array 欲排序数组
     */
    public static void SelectionSort(int[] array){
        int minValue;
        int minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            minValue = array[i];
            minIndex = i;
            // 寻找剩余元素中的最小元素。
            for (int j = i; j < array.length - 1; j++) {
                if (minValue > array[j + 1]) {
                    minValue = array[j + 1];
                    minIndex = j + 1;// 记录下最小元素的下标
                }
            }
            // 交换位置。
            array[minIndex] = array[i];
            array[i] = minValue;
        }
    }

    /**
     * 插入排序
     * <p>将数组分成有序表和无序表两部分。每次取出无序表中首元素与有序表中元素比较，然后插入到合适位置。
     * 要注意的是，由于是往数组插入元素，所以要移动数组中元素。为了减少循环，我们从有序表最后一个元素开始寻找
     * 插入位置。如果未找到，顺便就把有序表元素往后移。
     * @param array 欲排序数组
     */
    public static void InsertionSort(int[] array){
        int insertValue;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i;
            // 如果插入元素比有序表最后的元素小，继续往前查找
            while (insertIndex -1 >= 0 && insertValue < array[insertIndex - 1]){
                // 顺便移动数据
                array[insertIndex] = array[insertIndex - 1];
                insertIndex--;
                // 若已经比较了有序表中所有元素，在有序表首部插入。
            }
            // 找到位置了，插入。
            array[insertIndex] = insertValue;
        }
    }

    /**
     * 希尔排序
     * <p>对数组分gap(length/2)个组，对每个组排序。之后减小gap(gap/2)，再对新分的每个组排序。</p>
     * <p>以此类推，继续排序。<p/>
     * @param array 欲排序数组
     */
    public static void ShellSort(int[] array){
        int index;
        int value;
        // gap为步长(分组长度)，逐次取半
        for (int gap = array.length/2; gap > 0; gap /= 2){
            // 每个组自行排序。注意，此处是每个组交替排序，不是一个组结束排序再让另一个组排序。
            for (int i = gap; i < array.length; i++) {
                // 组内元素排序,使用(快速)插入排序
                index = i;
                value = array[i];
                while (index - gap >= 0 && value < array[index - gap]){
                    array[index] = array[index - gap];
                    index -= gap;
                    // 若已经比较了有序表中所有元素，在组内首部插入。
                }
                // 找到插入位置了。
                array[index] = value;
            }
        }
    }

    /**
     * 快速排序的接口样式方法
     * @param array 待排序数组
     */
    public static void QuickSort(int[] array){
        QuickSort(array,0, array.length-1);
    }

    /**
     * 快速排序的实现
     * @param array 待排序的数组
     * @param begin 数组头
     * @param end 数组尾
     */
    private static void QuickSort(int[] array, int begin, int end){
        if (begin >= 0 && begin < end && end < array.length){
            int left = begin; // 左下标
            int right = end; // 右下标
            int key = array[left]; // 以序列第一个值为基准
            while (left != right){
                // 从后往前寻找较小值移动
                while (left < right && array[right] >= key)
                    right--;
                if (left < right)
                    // 较小值移动到基准数前面。序列头往后移
                    array[left++] = array[right];
                // 从前往后寻找较大值移动
                while (left < right && array[left] <= key)
                    left++;
                if (left < right) {
                    // 较大值移动到基准数后面。序列尾往前移
                    array[right--] = array[left];
                }
            }
            // 所有元素都比较过后，此时left = right,在这个位置插入我们的基准元素
            array[left] = key;
            // 开始递归子序列
            QuickSort(array, begin, right-1);
            QuickSort(array, left+1, end);
        }
    }

    /**
     * 归并排序的接口样式方法
     * @param array 欲排序数组
     */
    public static void MergeSort(int[] array){
        // 等长的辅助数组
        int[] temp = new int[array.length];
        // 初始子序列长度为1
        int length = 1;
        // 轮趟归并
        while (length < array.length){
            // 一趟归并
            mergepass(array, temp, length);
            length *= 2; // 子序列长度加倍
            if (length < array.length){
                // 一趟归并，并将temp中元素归并回array中
                mergepass(temp, array, length);
                length *= 2; // 子序列长度加倍
            }
        }
    }

    /**
     * 一次归并算法实现
     * @param origin 由子序列组成的欲归并数组
     * @param target 存储归并后元素的数组
     * @param begin1 参与归并的第1个子序列首元素下标
     * @param begin2 参与归并的第2个子序列首元素下标
     * @param length 子序列长度
     */
    private static void merge(int[] origin, int[] target, int begin1, int begin2, int length){
        // first作为第1个子序列的下标，从子序列首元素开始
        int first = begin1;
        // second作为第2个子序列的下标，从子序列首元素开始
        int second = begin2;
        // last 作为归并后数组的下标
        int last = begin1;
        // 归并操作。
        while (first < begin1+length && second < begin2+length && second < origin.length){
            // 将两序列中较小的元素复制到target数组中
            if (origin[first] <= origin[second])
                target[last++] = origin[first++];
            else
                target[last++] = origin[second++];
        }
        // 程序执行到此处时，表示某个子序列的元素已经全部复制了，但另一个子序列还有未复制的元素。
        // 如果有未复制的元素，全部复制到target中。
        while (first < begin1+length && first < origin.length)
            target[last++] = origin[first++];
        while (second < begin2+length && second < origin.length)
            target[last++] = origin[second++];
    }

    /**
     * 一趟归并算法实现
     * @param origin 由子序列组成的欲归并数组
     * @param target 存储归并后元素的数组
     * @param length 子序列长度
     */
    private static void mergepass(int[] origin, int[] target, int length){
        // 从第一组子序列开始，每组调用  一次归并算法  ，直到每组子序列都归并了。
        for (int i = 0; i < origin.length; i += 2 * length)
            merge(origin, target, i, i+length, length);
    }

    /**
     * 基数排序
     * @param array 带排序数组
     */
    public static void RadixSort(int[] array) {
        // 十个桶，用二维数组表示
        int[][] bucket = new int[10][array.length];
        // 记录每轮排序各个桶放入的元素个数
        int[] elementCount = new int[10];
        // 从首元素开始，获取最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        // 获取最大值的位数
        int maxLength = (max + "").length();

        // 从最低数位开始(个，十，百，千。。。以此类推)，逐轮排序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 往桶里放入元素
            for (int j : array) {
                // 获取每个元素对应数位(个位，十位，百位，以此类推)上的值
                int digit = j / n % 10;
                // 将每个元素放到对应的桶中
                bucket[digit]/*第digit个桶*/[elementCount[digit]/*该桶的空位置*/] = j;
                elementCount[digit]++;
            }
            // 从桶里取出元素
            int index = 0;
            for (int k = 0; k < elementCount.length; k++) {
                // 桶中有元素，放回数组
                if (elementCount[k] != 0) {
                    for (int l = 0; l < elementCount[k]; l++)
                        array[index++] = bucket[k][l];
                }
                // 每轮结束后要将桶内元素清空
                elementCount[k] = 0;
            }
        }
    }
}
package test.application.sort;

import application.sort.SortAlgorithm;

import java.util.Arrays;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = ((int) (Math.random() * 1000));
        }
        //int[] arr = new int[]{3,2,1,4};
        System.out.println(Arrays.toString(arr));
        SortAlgorithm.RadixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
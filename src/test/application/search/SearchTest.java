package test.application.search;

import application.search.SearchAlgorithm;
import application.sort.SortAlgorithm;

import java.util.Arrays;

/**
 * @author Winter Yuan
 * @version 1.0
 */
public class SearchTest {
    public static void main(String[] args) {
        int[] arr = new int[]{23479, 75246, 94589, 12762, 45235, 19842, 29875, 18746};
        SortAlgorithm.QuickSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(SearchAlgorithm.InsertionValueSearch(arr, 100));
    }
}

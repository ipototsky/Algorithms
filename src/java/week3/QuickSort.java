package week3;

import java.util.Arrays;

import static utils.SortUtils.less;
import static utils.SortUtils.swap;

public class QuickSort {

    public static void sort(Comparable[] array, int start, int end) {
        if (start < end) {
            int pivot = partition(array, start, end);

            sort(array, start, pivot - 1);
            sort(array, pivot + 1, end);
        }
    }

    public static int partition(Comparable[] array, int start, int end) {
        int i = start;
        int j = start;

        while (j < end) {
            if (less(array[j], array[end])) {
                swap(array, i, j);
                i++;
            }
            j++;
        }
        swap(array, i, end);
        return i;
    }

    public static void fastSort(Comparable[] array, int start, int end) {
        int i = start;
        int j = end;

        int pivot = (end - start) / 2 + start;
        while (i < j) {
            while (i < pivot && less(array[i], array[pivot])) {
                i++;
            }
            while (j > pivot && less(array[pivot], array[j])) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            fastSort(array, start, j);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5, 9, 1, 6, 3, 7, 2, 8, 0, 4};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
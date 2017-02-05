package utils;

import java.util.Arrays;

/**
 * Created by ipototsky on 1/8/17.
 */
public class SortUtils {
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void swap(Comparable[] array, int i, int j) {
        Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static boolean isSorted(Comparable[] array) {
        return isSorted(array, 0, array.length);
    }

    public static boolean isSorted(Comparable[] array, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHsorted(Comparable[] array, int h) {
        for (int i = h + 1; i < array.length; i += h) {
            if (less(array[i], array[i - h])) {
                return false;
            }
        }
        return true;
    }

    public static void print(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }
}

package week4;

import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.print;

/**
 * Created by ivan.pototsky on 07.02.2017.
 */
public class HeapSort {

    public static void sort(Comparable[] array) {
        int length = array.length;
        //all nodes except leafs
        for (int i = length/2; i >= 1; i--) {
            sink(array, i, length);
        }
        while (length > 1) {
            swap(array, 1, length--);
            sink(array, 1, length);
        }
    }

    private static void sink(Comparable[] array, int k, int length) {
        while (2*k < length) {
            int j = 2*k;
            if (j < length && less(array, j, j+1)) {
                j++;      //choose greater child
            }
            if (!less(array, k, j)) break;
            swap(array, j, k);
            k = j;
        }
    }

    private static boolean less(Comparable[] array, int i, int j) {
        return array[i-1].compareTo(array[j-1]) < 0;
    }

    private static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i-1];
        array[i-1] = array[j-1];
        array[j-1] = temp;
    }

    public static void main(String[] args) {
        Comparable[] array = readArrayFromFile(args[0]);
        print(array);
        sort(array);
        print(array);
    }
}
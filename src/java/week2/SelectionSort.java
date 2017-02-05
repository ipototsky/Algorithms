package week2;

import edu.princeton.cs.algs4.Stopwatch;
import utils.SortUtils;

import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.*;

/**
 * Created by ipototsky on 1/9/17.
 */
public class SelectionSort {

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < array.length; j++){
                if (less(array[j], array[indexOfMin])) {
                    indexOfMin = j;
                }
            }
            swap(array, i, indexOfMin);
        }
        assert isSorted(array);
    }

    public static void main(String[] args) {
        Comparable[] array = readArrayFromFile(args[0]);
        Stopwatch stopwatch = new Stopwatch();
        sort(array);
        SortUtils.print(array);
        System.out.println(stopwatch.elapsedTime());
    }
}

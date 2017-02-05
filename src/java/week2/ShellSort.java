package week2;

import edu.princeton.cs.algs4.Stopwatch;

import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.*;

/**
 * Created by ipototsky on 1/14/17.
 */
public class ShellSort {


    public static void sort(Comparable[] array) {
        int size = array.length;
        int h = 1;
        while (h < size / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {

            for (int i = h; i < size; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }
            assert isHsorted(array, h);
            h /= 3;
        }
        assert isSorted(array);
    }


    public static void main(String[] args) {
        Comparable[] array = readArrayFromFile(args[0]);
        Stopwatch stopwatch = new Stopwatch();
        sort(array);
        print(array);
        System.out.println(stopwatch.elapsedTime());
    }
}

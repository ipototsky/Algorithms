package week1.quiz;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import week1.BinarySearch;

import java.util.Arrays;

/**
 * Created by ipototsky on 1/5/17.
 * N^2 log N algorithm.
 */
public class ThreeSumFast {

    public static int count(int[] array, int sum) {
        int size = array.length;
        int count = 0;
        Arrays.sort(array);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size - 1; j++) {

                int k = BinarySearch.search(array, j + 1, size - 1, sum -(array[i] + array[j]));
                if (k != -1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
//        int[] a = StdIn.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(count(a, 0));
        System.out.println(stopwatch.elapsedTime());
    }
}

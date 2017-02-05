package week1.quiz;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by ipototsky on 1/5/17.
 *  N^2 algorithm.
 */
public class ThreeSumFastest {
    private static final int SUM = 0;

    //Counting number of three-tuple with sum = 0
    public static int count(int[] array, int sum) {
        int N = array.length;
        int count = 0;
        Arrays.sort(array);
        for (int i = 0; i < N - 2; i++) {
            int lo = i + 1;
            int hi = N - 1;
            while (lo < hi) {
                if (array[i] + array[lo] + array[hi] < sum) {
                    lo++;
                } else if (array[i] + array[lo] + array[hi] > sum) {
                    hi--;
                } else {
                    count++;
                    lo++;
                    hi--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(count(a, SUM));
        System.out.println(stopwatch.elapsedTime());
    }
}

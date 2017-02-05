package week1.quiz;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import week1.BinarySearch;

/**
 * Created by ipototsky on 1/5/17.
 */
public class BitonicMax {

    // create a bitonic array of size N
    public static int[] initBitonic(int N) {
        int mid = StdRandom.uniform(N);
        int[] a = new int[N];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i-1] + 1 + StdRandom.uniform(9);
        }

        if (mid > 0) a[mid] = a[mid-1] + StdRandom.uniform(10) - 5;

        for (int i = mid + 1; i < N; i++) {
            a[i] = a[i-1] - 1 - StdRandom.uniform(9);
        }
        return a;
    }

    // find the index of the maximum in a bitonic subarray a[lo..hi]
    private static int max(int[] a, int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int mid = lo + (hi - lo)/2;
        if (a[mid] < a[mid+1]) {
            return max(a, mid + 1, hi);
        } else if (a[mid] > a[mid+1]) {
            return max(a, lo, mid);
        }
        return mid;
    }

    //Standard solution 3 lg n.
    public static int search(int[] a, int value) {
        int indexOfMax = max(a, 0, a.length);
        int result = BinarySearch.search(a, 0, indexOfMax, value);
        if (result == -1) {
            result = BinarySearch.descendingSearch(a, indexOfMax, a.length - 1, value);
        }
        return result;
    }

    //Fast solution 2 lg n. Without finding max.
    public static int fastSearch(int[] a, int lo, int hi, int value) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == value) {
            return mid;
        }

        if (lo >= hi - 1) {
            return -1;
        }

        if (value > a[mid]) {
            if (a[mid] < a[mid + 1]) {
                return fastSearch(a, mid + 1, hi, value);
            } else {
                return fastSearch(a, lo, mid - 1, value);
            }
        }
        int result = BinarySearch.search(a, lo, mid, value);
        if (result == -1) {
            result = BinarySearch.descendingSearch(a, mid, hi, value);
        }
        return result;
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int[] a = initBitonic(N);
        Stopwatch stopwatch = new Stopwatch();
//        StdOut.println("max = " + a[max(a, 0, N-1)]);
//        int index = fastSearch(a, 0, N-1, 932543);
        int index = search(a, 93782543);
        StdOut.println(index == -1 ? "Not found" : "index = " + index + "  value=" + a[index]);
        System.out.println(stopwatch.elapsedTime());
    }

}

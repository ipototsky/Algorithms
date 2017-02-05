package week2.quiz;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Created by ivan.pototsky on 27.01.2017.
 */
public class DutchNationalFlag {
    private static final String RED = "RED";
    private static final String WHITE = "WHITE";
    private static final String BLUE = "BLUE";

    private static int colorCounter = 0;
    private static int swapCounter = 0;

    public static void sort(int[] array) {
        int lo = 0;
        int mid = 0;
        int hi = array.length - 1;
        while (mid <= hi) {
            String color = color(array[mid]);
            if (WHITE.equals(color)) {
                mid++;
            } else if (RED.equals(color)) {
                swap(array, lo, mid);
                lo++;
                mid++;
            } else { // BLUE
                swap(array, hi, mid);
                hi--;
            }
        }
    }

    public static int[] generate(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = StdRandom.uniform(3);
        }
        return array;
    }

    public static void main(String[] args) {
        int N = 10;
        if (args.length != 0) {
            N = Integer.valueOf(args[0]);
        }
        int[] array = generate(N);
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("Number of swaps = " + swapCounter);
        System.out.println("Get color counter = " + colorCounter);
    }

    public static String color(int i) {
        colorCounter++;
        return i == 0 ? RED :
                i == 1 ? WHITE : BLUE;
    }

    public static void swap(int[] array, int i, int j) {
        swapCounter++;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
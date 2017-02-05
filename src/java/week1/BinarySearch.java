package week1;

/**
 * Created by ipototsky on 1/5/17.
 */
public class BinarySearch {

    public static int search(int[] array, int key) {
        int lo = 0;
        int hi = array.length - 1;

        return search(array, lo, hi, key);
    }

    public static int search(int[] array, int lo, int hi, int value) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (value < array[mid]) {
                hi = mid - 1;
            } else if (value > array[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int descendingSearch(int[] array, int lo, int hi, int value) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (value < array[mid]) {
                lo = mid + 1;
            } else if (value > array[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

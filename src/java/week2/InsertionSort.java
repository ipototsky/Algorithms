package week2;

import edu.princeton.cs.algs4.Stopwatch;


import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.*;
import static utils.SortUtils.isSorted;

public class InsertionSort {

  public static void sort(Comparable[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
        swap(array, j, j - 1);
      }
    }
    assert isSorted(array);
  }

  public static void main(String[] args) {
    Comparable[] array = readArrayFromFile(args[0]);
    Stopwatch stopwatch = new Stopwatch();
    sort(array);
//        print(array);
    System.out.println(stopwatch.elapsedTime());
  }
}
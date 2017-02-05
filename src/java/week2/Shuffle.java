package week2;

import edu.princeton.cs.algs4.StdRandom;

import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.print;
import static utils.SortUtils.swap;

/**
 * Created by ipototsky on 1/14/17.
 */
public class Shuffle {


    public static void shuffle(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int r = StdRandom.uniform(i + 1);
            swap(array, i, r);
        }
    }

    public static void main(String[] args) {
        Comparable[] array = readArrayFromFile(args[0]);
        print(array);

        ShellSort.sort(array);
        print(array);

        shuffle(array);
        print(array);
    }
}

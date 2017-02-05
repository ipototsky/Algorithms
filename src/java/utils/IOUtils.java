package utils;

import edu.princeton.cs.algs4.In;

import java.util.stream.Stream;

/**
 * Created by ipototsky on 1/13/17.
 */
public class IOUtils {
    private static final String WORDS_FILE_NAME = "words3.txt";

    public static Comparable[] readArrayFromFile(String fileName) {
        String[] strings = new In(fileName).readAllStrings();
        if (WORDS_FILE_NAME.equals(fileName)) {
            return strings;
        }
        Stream<Integer> stream = Stream.of(strings).map(entry -> Integer.valueOf(entry));
        return stream.toArray(size -> new Integer[size]);
    }
}

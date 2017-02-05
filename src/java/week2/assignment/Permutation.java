package week2.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ivan.pototsky on 17.01.2017.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int i = 0;
        while (!StdIn.isEmpty()) {
            i++;
            String string = StdIn.readString();
            if (i > k) {
                int random = StdRandom.uniform(i);
                if (random < k) {
                    queue.dequeue();
                    queue.enqueue(string);
                }
            } else {
                queue.enqueue(string);
            }
        }

        for (i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
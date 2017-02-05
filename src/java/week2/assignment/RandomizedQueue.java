package week2.assignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ipototsky on 1/16/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] array;

    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return n <= 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (n == array.length) {
            resize(n *2);
        }
        array[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(n);
        Item result = array[randomIndex];
        n--;
        if (randomIndex != n) {   // switch with last non-empty element, to avoid empty gaps
            array[randomIndex] = array[n];
        }
        array[n] = null;
        if (n > 0 && n == array.length / 4) resize(array.length / 2);
        return result;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(n);
        return array[randomIndex];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i;
        private Item[] copy;

        public RandomIterator() {
            copy = (Item[]) new Object[n];
            System.arraycopy(array, 0, copy, 0, n);
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return i < copy.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i++];
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(array, 0, copy, 0, n);
        array = copy;
    }

    public static void main(String[] args) {
    }
}
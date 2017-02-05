package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ipototsky on 1/15/17.
 */
public class ResizingArrayStack<T> implements Iterable<T> {
    private int index;
    private T[] array;

    public ResizingArrayStack() {
        array = (T[]) new Object[2];
    }

    public void push(T item) {
        if (index == array.length) {
            resize(array.length * 2);
        }
        array[index++] = item;
    }

    public T pop() {
        T result = array[--index];
        array[index] = null;

        if (index == array.length / 4) {
            resize(array.length / 2);
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        System.arraycopy(array, 0, copy, 0, index);
        array = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("-".equals(s)) {
                StdOut.println(stack.pop());
            } else {
                stack.push(s);
            }
        }
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int index = array.length;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[--index];
        }
    }
}

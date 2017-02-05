package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static utils.IOUtils.readArrayFromFile;
import static utils.SortUtils.print;

/**
 * Created by ipototsky on 1/15/17.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private int head;
    private int tail;
    //number of elements
    private int size;
    private Item[] array = (Item[]) new Object[2];


    public void enqueue(Item item) {
        if (size == array.length) {
            resize(size *2);
        }
        array[tail++] = item;
        size++;
        if (tail == array.length) {
            tail = 0;
        }
    }

    public Item dequeue() {
        Item result = array[head];
        array[head] = null;
        head++;
        size--;
        if (head == array.length) {
            head = 0;
        }
        return result;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(array, 0, copy, 0, size);
        array = copy;
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue();
        Comparable[] array = readArrayFromFile(args[0]);
        print(array);
        for (Comparable comparable : array) {
            queue.enqueue((String) comparable);
        }
        for (String string : queue) {
            System.out.println(string);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[index++];
        }
    }
}

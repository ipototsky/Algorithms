package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ipototsky on 1/15/17.
 */
public class Queue<Item> implements Iterable<Item>{
    private Node first;
    private Node last;

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item result = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return result;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        private Item item;
        private Node next;
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("-".equals(s)) {
                StdOut.println(queue.dequeue());
            } else {
                queue.enqueue(s);
            }
        }
    }
}

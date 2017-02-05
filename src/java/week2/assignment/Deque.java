package week2.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ipototsky on 1/16/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item result = first.item;

        Node oldFirst = first;
        first = oldFirst.next;
        oldFirst.next = null;

        size--;
        if (isEmpty()) {   // first(newFirst) != null
            last = null;
        } else {
            first.prev = null;
        }
        return result;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item result = last.item;

        Node oldLast = last;
        last = oldLast.prev;
        oldLast.prev = null;

        size--;
        if (isEmpty()) {  // last(newLast) != null
            first = null;
        } else {
            last.next = null;
        }
        return result;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item result = current.item;
            current = current.next;
            return result;
        }
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
}
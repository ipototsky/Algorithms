package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ipototsky on 1/15/17.
 */
public class Stack<Item> implements Iterable<Item> {
    private int size;
    private Node root;

    public void push(Item value) {
        Node oldRoot = root;
        root = new Node();
        root.item = value;
        root.next = oldRoot;
        size++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Item result = root.item;
        root = root.next;
        size--;
        return result;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if ("-".equals(s)) {
                StdOut.println(stack.pop());
            } else {
                stack.push(s);
            }
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = root;

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
}
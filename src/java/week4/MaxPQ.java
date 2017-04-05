package week4;

import java.util.NoSuchElementException;

import static utils.SortUtils.less;

/**
 * Created by ivan.pototsky on 06.02.2017.
 * Max Priority Queue.
 * Binary Heap representation.
 */
public class MaxPQ<Val extends Comparable<Val>> {
    private Val[] array;
    private int n;

    //Fixed capacity
    //TODO resize array implementation.
    public MaxPQ(int capacity) {
        this.array = (Val[]) new Comparable[capacity + 1];   //0'th element is null for simplicity
    }

    public Val max() {
        if (isEmpty()) throw new NoSuchElementException();
        return array[1];
    }

    public void insert(Val value) {
        array[++n] = value;
        swim(n);
    }

    public Val delMax() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Val max = array[1];
        swap(1, n);
        array[n--] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if(j < n && less(array[j], array[j+1])) {
                j++;
            }
            if (!less(array[k], array[j])) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Val temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> queue = new MaxPQ<>(20);
        queue.insert(30);
        queue.insert(-30);
        queue.insert(-20);
        queue.insert(-10);
        queue.insert(40);
        queue.insert(0);
        queue.insert(10);
        queue.insert(15);
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
        queue.delMax();
    }
}
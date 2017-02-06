package week4;

import java.util.NoSuchElementException;

import static utils.SortUtils.less;
import static utils.SortUtils.swap;

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

    public void insert(Val value) {
        array[++n] = value;
        up(n);
    }

    public Val delMax() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Val max = array[1];
        swap(array, 1, n);
        array[n--] = null;
        down(1);
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void up(int k) {
        while (hasParent(k) && less(parent(k), array[k])) {
            swap(array, k, parentIndex(k));
            k = parentIndex(k);
        }
    }

    private void down(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if(j < n && less(array[2*k], array[2*k+1])) {
                j++;
            }
            if (!less(array[k], array[j])) {
                break;
            }
            swap(array, k, j);
            k = j;
        }
    }

    //TODO remove this helpers.
    private boolean hasParent(int i) {
        return i > 1;
    }

    private Val parent(int i) {
        return array[parentIndex(i)];
    }

    private int parentIndex(int i) {
        return i/2;
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
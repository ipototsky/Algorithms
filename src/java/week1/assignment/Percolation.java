package week1.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by ipototsky on 1/2/17.
 */
public class Percolation {
    private static final int OPEN = 1;                // 001
    private static final int CONNECT_TO_TOP = 2;      // 010
    private static final int CONNECT_TO_BOTTOM = 4;   // 100
    private static final int PERCOLATE = 7;           // 111
    private boolean percolateFlag;
    private byte[] statuses;

    private int number;
    private int opened;
    private WeightedQuickUnionUF quickUinon;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        number = n;
        statuses = new byte[n*n];
        quickUinon = new WeightedQuickUnionUF(n*n);
    }

    private int xyTo1D(int row, int col) {
        return (row-1)*number+col - 1;
    }

    private void validate(int row, int col) {
        if (!(0 < row && row <= number) || !(0 < col && col <= number)) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        int currentIndex = xyTo1D(row, col);
        if (isOpen(statuses[currentIndex])) {
            return;
        }
        statuses[currentIndex] |= OPEN;
        opened++;

        byte fullStatus = OPEN;
        byte status = 0;
        if (row > 1) {
            status = statuses[quickUinon.find(currentIndex - number)];
            if (isOpen(status)) {
                fullStatus |= status;
                quickUinon.union(currentIndex, currentIndex - number);
            }
        }
        if (col < number) {
            status = statuses[quickUinon.find(currentIndex + 1)];
            if (isOpen(status)) {
                fullStatus |= status;
                quickUinon.union(currentIndex, currentIndex + 1);
            }
        }
        if (row < number) {
            status = statuses[quickUinon.find(currentIndex + number)];
            if (isOpen(status)) {
                fullStatus |= status;
                quickUinon.union(currentIndex, currentIndex + number);
            }
        }
        if (col > 1) {
            status = statuses[quickUinon.find(currentIndex - 1)];
            if (isOpen(status)) {
                fullStatus |= status;
                quickUinon.union(currentIndex, currentIndex - 1);
            }
        }
        if (row == 1) {
            fullStatus |= CONNECT_TO_TOP;
        }
        if (row == number) {
            fullStatus |= CONNECT_TO_BOTTOM;
        }
        statuses[quickUinon.find(currentIndex)] = fullStatus;
        if ((fullStatus & PERCOLATE) == PERCOLATE) {
            percolateFlag = true;
        }
    }

    private boolean isOpen(byte status) {
        return (status & OPEN) == OPEN;
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        return (statuses[index] & OPEN) == OPEN;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        return (statuses[quickUinon.find(index)] & CONNECT_TO_TOP) == CONNECT_TO_TOP;
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        return percolateFlag;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!percolation.isOpen(p, q)) {
                percolation.open(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}

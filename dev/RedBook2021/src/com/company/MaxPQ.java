package com.company;
// extends: bounded type parameter
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] heap;
    private int N;

    public MaxPQ(int capacity) {
        heap = (Key[]) new Comparable[capacity+1];
    }

    public void insert(Key x) {
        heap[++N] = x;
        swim(N);
    }

    public Key delMax() {
        // exch root w end then sink
        Key max = heap[1];
        exch(1, N--);
        sink(1);
        heap[N + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}

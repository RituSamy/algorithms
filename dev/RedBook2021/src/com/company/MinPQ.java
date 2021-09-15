package com.company;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] heap;
    private int N;

    public MinPQ(int capacity) {
        heap = (Key[]) new Comparable[capacity+1];
    }

    public void insert(Key x) {
        heap[++N] = x;
        swim(N);
    }

    public int size() { return heap.length; }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key delMin() {
        // exch root w end then sink
        Key min = heap[1];
        exch(1, N--);
        sink(1);
        heap[N + 1] = null;
        return min;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j+1, j)) j++;
            if (less(k, j)) break;
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

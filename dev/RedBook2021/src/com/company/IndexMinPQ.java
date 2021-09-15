package com.company;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;
    private Key[] keys; // priority of i
    private int[] pq; // index of key in heap position i
    private int[] qp; // heap position of key with index i. pq[qp[i]] = i

    public IndexMinPQ(int capacity) {
        keys = (Key[]) new Comparable[capacity+1];
        pq = new int[capacity+1];
        qp = new int[capacity+1];

        for (int i = 0; i < capacity+1; i++) {
            qp[i] = -1; // why do we do this?
        }
    }

    public void insert(int i, Key key) {
        N++;
        // add the new index to the end of pq[]. this essentially is adding the new
        // key at the bottom of the priority queue
        pq[N] = i;
        // thus, the new key is at the last heap position.
        qp[i] = N;
        // add the key of course, at the desired index.
        keys[i] = key;
        // now that the new key has been added to the bottom of the heap,
        // swim it up to the correct position.
        swim(N);
    }

    // remove min and return associated index
    int delMin() {
        // top of pq = minimum value. pq[] holds indices, so pq[1]
        // isn't the minimum value, it's the index of the minimum value.
        // in other words, the index of the key at heap position 1. (a.k.a. minimum)
        int indexOfMin = pq[1];
        // exch root node w last leaf node, then sink
        exch(1, N--);
        sink(1);
        // pq[N+1] is the index of the now removed min. thus, keys[that index]
        // should be null.
        keys[pq[N + 1]] = null;
        qp[pq[N+1]] = -1; // why do we do this?
        pq[N+1] = 0;
        return indexOfMin;
    }

    public Key min() {
        return keys[pq[1]];
    }

    public void decreaseKey(int k, Key key) {
        // swim(qp[k]) qp[] for the heap position. instead of swim(N), which is bringing the bottom element up to the top of the tree,
        // swim(qp[k]) = swim(heap position of k).
        // swim, not sink, because it goes up.
        keys[k] = key;
        swim(qp[k]);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            s += keys[i] + " , ";
        }
        return s;
    }

    // is k an index on the priority queue?
    boolean contains(int k) {
        return qp[k] != -1;
    }

    public boolean isEmpty() {
        return N == 0;
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
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        // swap the heap positions
        int tempq = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = tempq;
    }


}
